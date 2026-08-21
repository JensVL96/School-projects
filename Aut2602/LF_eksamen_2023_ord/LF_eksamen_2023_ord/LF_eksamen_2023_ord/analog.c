/*
 * analog.c
 *
 * Created: 31.08.2022 11:15:52
 *  Author: pol022
 */ 

#include <avr/io.h>
#include "analog.h"
#include <avr/interrupt.h>
#include <util/delay.h>
#include "usart.h"

//linear transform from the set A to the set B with scaling accordingly
int32_t linear_map(int32_t value, int32_t a_min,int32_t a_max,int32_t b_min, int32_t b_max){
	return b_min + (b_max - b_min)*(value - a_min)/(a_max - a_min);
}

// adc functions
void adc_init_freerunning(uint8_t channel){
		VREF.ADC0REF |= VREF_REFSEL_VDD_gc;
		_delay_us(25);
		ADC0.CTRLA |= ADC_FREERUN_bm;
		ADC0.CTRLC = ADC_PRESC_DIV16_gc;
		ADC0.MUXPOS = channel;
		ADC0.CTRLA |= ADC_ENABLE_bm;
		ADC0.COMMAND |= ADC_STCONV_bm; // starting _first_ conversion (Free-running mode)
		//ADC0.INTCTRL |= ADC_RESRDY_bm;
}

void adc_init_single_conversions(uint8_t channel){
	VREF.ADC0REF |= VREF_REFSEL_VDD_gc;
	_delay_us(25);
	ADC0.CTRLC |= ADC_PRESC_DIV2_gc;
	ADC0.MUXPOS = channel;
	ADC0.CTRLA |= ADC_ENABLE_bm;	
	ADC0.INTCTRL |= ADC_RESRDY_bm; // interrupt on result ready
	ADC0.COMMAND |= ADC_STCONV_bm; // starting _first_ conversion
}


ISR(ADC0_RESRDY_vect){
	result = ADC0.RES;
	//ADC0.COMMAND |= ADC_STCONV_bm;// starting again.
	res_ready = 1;
}

void adc_switch_channel(uint8_t channel){
	ADC0.MUXPOS = channel;
}
	
// polling!
uint16_t adc_get_result(){
	while(!(ADC0.INTFLAGS & ADC_RESRDY_bm))// waiting
		;
	return ADC0.RES;
}
