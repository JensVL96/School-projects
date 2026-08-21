/*
 * ccl.c
 *
 * Created: 08.10.2021 11:17:02
 *  Author: pol022
 */ 

#include <avr/io.h>
#include "ccl.h"
#include "usart.h"
#include <avr/interrupt.h>

void ccl2_button_init(){
	//disable before configuration to ensure stability
	CCL.LUT2CTRLA &= ~(1<<CCL_ENABLE_bp); 
	CCL.LUT2CTRLB |= CCL_INSEL1_IN1_gc | CCL_INSEL0_IN0_gc;
	CCL.LUT2CTRLC |= CCL_INSEL2_IN2_gc;
	CCL.TRUTH2 |= (1<<3) | (1<<5) | (1<<6);// two buttons pressed 
	CCL.INTCTRL0 |= CCL_INTMODE2_RISING_gc;	
	CCL.LUT2CTRLA |= (1<<CCL_ENABLE_bp); // Edgedet and Filtsel not enabled!
	CCL.CTRLA |= (1<<CCL_ENABLE_bp) | CLKCTRL_RUNSTDBY_bm; // #_#_#_#_#_#_#
}

ISR(CCL_CCL_vect){
	CCL.INTFLAGS |= (1<<CCL_INT2_bp);
	//printf("buttons pressed\n");
}

