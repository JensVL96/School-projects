/*
 * analog.h
 *
 * Created: 31.08.2022 11:15:38
 *  Author: pol022
 */ 


#ifndef ANALOG_H_
#define ANALOG_H_

volatile uint16_t result;
volatile uint8_t res_ready;

//linear transform from the set A to the set B with scaling accordingly
int32_t linear_map(int32_t value, int32_t a_min,int32_t a_max,int32_t b_min, int32_t b_max);

// adc functions
void adc_init_freerunning(uint8_t channel);
void adc_init_single_conversions(uint8_t channel);
void adc_switch_channel(uint8_t channel);
uint16_t adc_get_result();
#endif /* ANALOG_H_ */
