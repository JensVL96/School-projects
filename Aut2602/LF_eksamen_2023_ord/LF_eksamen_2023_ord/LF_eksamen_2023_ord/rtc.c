/*
 * rtc.c
 *
 * Created: 14.09.2021 15:58:05
 *  Author: pol022
 */ 

#include "rtc.h"
#include <avr/cpufunc.h>
#include <avr/io.h>
#include <avr/interrupt.h>
#include <stdio.h>
#include "usart.h"


void rtc_clock_init(){
	while(CLKCTRL.MCLKSTATUS & CLKCTRL_XOSC32KS_bm)
	{
		;
	}
	ccp_write_io((void *) &CLKCTRL.XOSC32KCTRLA, 1<<CLKCTRL_ENABLE_bp | 0<<CLKCTRL_SEL_bp);// starting external crystal oscillator
	RTC.CLKSEL = RTC_CLKSEL_XOSC32K_gc;
}

/* simplified implementation*/
void rtc_init(){
	RTC.CTRLA = RTC_RTCEN_bm;
	while(RTC.STATUS > 0){
		;
	}
	//RTC.CTRLA |= RTC_PRESCALER_DIV1_gc;
	RTC.CTRLA |= RTC_PRESCALER_DIV32768_gc;
	RTC.PER = 1;
	RTC.CNT = 0;
	RTC.INTCTRL = RTC_OVF_bm;
}

ISR(RTC_CNT_vect){
	RTC.INTFLAGS = RTC_OVF_bm;
	tick = 1;
}
