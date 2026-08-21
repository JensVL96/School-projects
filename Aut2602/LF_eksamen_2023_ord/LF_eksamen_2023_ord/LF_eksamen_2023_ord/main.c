/*
 * LF_eksamen_2023_ord.c
 *
 * Created: 21.11.2023 08:43:05
 * Author : pol022
 */ 

#include <avr/io.h>
#define F_CPU 4000000UL
#include <util/delay.h>
#include <avr/interrupt.h>
#include "usart.h"
#include "analog.h"
#include "pwm.h"
#include "board.h"
#include "rtc.h"
#include "ccl.h"
#include <avr/sleep.h>

void leds_init(){
	LED_PORT.DIR |= (1<<LED0_bp) | (1<<LED1_bp) | (1<<LED2_bp) | (1<<LED3_bp);
	LED_PORT.PINCONFIG |= PORT_INVEN_bm;
	LED_PORT.PINCTRLSET |= (1<<LED0_bp) | (1<<LED1_bp) | (1<<LED2_bp) | (1<<LED3_bp);
}

uint16_t message2num3(char message[]){
	printf("%d\n",(message[2]-'0')*100 + (message[3]-'0')*10 + message[4]-'0');
	return (message[2]-'0')*100 + (message[3]-'0')*10 + message[4]-'0';
}

uint8_t message2led(char message[]){
	printf("%d\n", message[0]-'0');
	return (message[0]-'0');
}


void oppgave3a(){
	// trenger leds
	// trenger timer
	// trenger rx-interrupt og noe tolkning av tegn
	uint8_t message_led;
	uint16_t message_sec;
	leds_init();
	usart_usb_init();
	printf("start\n");
	rtc_clock_init();
	rtc_init();
	sei();
	while(1)
	{
		if (usb_message_ready)
		{
			message_led = message2led(usb_message);
			message_sec	= message2num3(usb_message);
			if(message_sec>0){
				RTC.PER = message_sec-1;// adjusting for one tick
				RTC.CNT = 0; // nullstille counter;
				LED_PORT.OUT |= (1<<message_led);
			}
			usb_message_ready = 0;
			
		}
		if(tick){
			LED_PORT.OUT &= ~(1<<message_led);
			tick = 0;
		}
		
	}
	
}

// oppgave b
void pwm_2_servo_init(){
	// selecting where TCA0 WO-pins is distributed
	PORTMUX.TCAROUTEA |= PORTMUX_TCA0_PORTE_gc;
	// setting these pins as output and inverting for easier functionality
	TRANSISTOR_PORT.DIR |= (1<<TRANSISTOR_Q1_bp) | (1<<TRANSISTOR_Q2_bp);
	TRANSISTOR_PORT.PINCONFIG |= PORT_INVEN_bm; // transistors invert output
	TRANSISTOR_PORT.PINCTRLSET |= (1<<TRANSISTOR_Q1_bp) | (1<<TRANSISTOR_Q2_bp);
	// pwm_frequency should be 50 Hz
	// starting timer and selecting no division:
	TCA0.SINGLE.CTRLA = TCA_SINGLE_CLKSEL_DIV2_gc | TCA_SINGLE_ENABLE_bm;
	// enabling output pins on compare channels and setting wave generation mode to single-slope pwm
	TCA0.SINGLE.CTRLB = TCA_SINGLE_CMP0_bm | TCA_SINGLE_CMP1_bm | TCA_SINGLE_WGMODE_SINGLESLOPE_gc;
	// interrupts not necessary in this mode
	TCA0.SINGLE.PER = 40000; // this gives PWM frequency = 50Hz
	TCA0.SINGLE.CMP0BUF = 3000;
	TCA0.SINGLE.CMP1BUF = 3000;
}


void oppgave3b(){
	// bruker analoge innganger 0 og 1;
	pwm_2_servo_init();
	adc_init_freerunning(1);
	while (1)
	{
		TCA0.SINGLE.CMP0 = linear_map(adc_get_result(),0,4095,2000,4000);
		adc_switch_channel(1);
		_delay_ms(50);
		TCA0.SINGLE.CMP1 = linear_map(adc_get_result(),0,4095,2000,4000);
		adc_switch_channel(0);
		_delay_ms(50);
	}
}

void oppgave3c(){
	SW_PORT.PINCONFIG |= PORT_INVEN_bm | PORT_PULLUPEN_bm;
	SW_PORT.PINCTRLSET |= (1<<SW0_bp) | (1<<SW1_bp) | (1<<SW2_bp);
	ccl2_button_init();
	leds_init();
	SLPCTRL.CTRLA |= SLPCTRL_SEN_bm | SLPCTRL_SMODE_STDBY_gc;
	sei();
	sleep_cpu();
	while (1)
	{
		for(uint8_t i = 0; i<10; i++){
			LED_PORT.OUT ^= (1<<LED0_bp);
			_delay_ms(1000);
		}
		LED_PORT.OUT &= ~(1<<LED0_bp);
		sleep_cpu();
		
	}
}

int main(void)
{
    /* Replace with your application code */
	//oppgave3a();
	//oppgave3b();
	oppgave3c();
    while (1) 
    {
    }
}

