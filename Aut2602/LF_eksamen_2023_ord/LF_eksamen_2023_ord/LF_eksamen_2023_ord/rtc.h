/*
 * rtc.h
 *
 * Created: 14.09.2021 15:57:53
 *  Author: pol022
 */ 


#ifndef RTC_H_
#define RTC_H_

#include <avr/io.h>


volatile uint16_t counter;
volatile uint8_t tick;
volatile uint8_t pit_tick;

volatile uint8_t sec, min, hour;

void rtc_clock_init();
void rtc_init();
//min freq = 1Hz max = 32.768kHz
uint8_t rtc_init_set_high_freq(uint32_t freq_Hz);

/* Generalized frequency function is left as an exercise for the students
//min freq = 15 uHz, max = 32.768kHz
uint8_t rtc_init_set_freq(uint32_t freq_Hz, int8_t decade_multiplier);
//min freq = 15 uHz, max 4295Hz
uint8_t rtc_init_set_low_freq(uint32_t freq_uHz);
*/


void pit_init();







#endif /* RTC_H_ */