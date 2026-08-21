/*
 * usart.c
 *
 * Created: 24.08.2022 14:14:12
 *  Author: pol022
 */ 

#include <avr/io.h>
#include "usart.h"
#include <avr/interrupt.h>


void usart_usb_init(){
	/* Necessary configuration*/
	USART3.BAUD = 1667; // Baudrate 9600 // F_CPU = 4M
	//USART3.BAUD = 6667;
	USART3.CTRLB |= USART_RXEN_bm | USART_TXEN_bm | USART_RXMODE_NORMAL_gc; //enable RX TX
	PORTB.DIR |= (1<<TX_PIN_bp);
	//PORTB.PINCONFIG |= PORT_PULLUPEN_bm;
	//PORTB.PINCTRLSET |= (1<<RX_PIN_bp);
	/* interrupt on RX*/
	USART3.CTRLA |= USART_RXCIE_bm;
	/* printf */
	stdout = &new_std_out; // address to struct
}


void usart_usb_transmit(char c){
	while(!(USART3.STATUS & USART_DREIF_bm)){
		;
	}
	USART3.TXDATAL = c;
}


/* transmitting*/
void usart_usb_transmit_char_array(uint8_t addEndLine,char string[], uint8_t length){
	for(uint8_t i = 0; i<length; i++){
		usart_usb_transmit(string[i]);
	}
	if(addEndLine)
		usart_usb_transmit('\n');
}


/* rx-interrupt with some handling. see main*/
ISR(USART3_RXC_vect){
	char tmp = USART3.RXDATAL;
	usart_usb_transmit(tmp);
	switch (tmp)
	{
		case '<':
			marker_pos = 0;
			break;
		case '>':
			usb_message_ready = 1;
			usb_message[marker_pos] = 0;
			break;
		default:
			usb_message[marker_pos] = tmp;
			marker_pos++;
	}
}

static uint8_t usart_usb_transmit_printf(char c, FILE *stream){
	usart_usb_transmit(c);
	return 0;
}
