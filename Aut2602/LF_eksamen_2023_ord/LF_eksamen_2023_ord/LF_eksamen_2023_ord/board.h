/*
 * board.h
 *
 * Created: 26.08.2021 09:26:08
 *  Author: pol022
 */ 


#ifndef BOARD_H_
#define BOARD_H_

//#define F_CPU 4000000UL

/*positions of different components*/

#define CURIOSITY_IO_PORT PORTB
#define CURIOSITY_LED_POS 2
#define CURIOSITY_BUTTON_POS 3

#define ANALOG_PORT PORTD

#define DAC_POS 6
#define LIGT_SENSOR_POS 3
#define MIC_POS 7 
#define POT_POS 4 // R1
#define TEMP_POS 5

#define LED_PORT PORTC
#define LED0_bp 0
#define LED1_bp 1
#define LED2_bp 2
#define LED3_bp 3

// Buttons inputs are connected to LUT4
#define SW_PORT PORTD
#define SW0_bp 0
#define SW1_bp 1
#define SW2_bp 2

#define RS485_DIR_PORT PORTC
#define RS485_DIR_bp 7

#define RS485_USART_PORT PORTC
#define RS485_RX_bp 5
#define RS485_TX_bp 4

#define RPI_USART_PORT PORTF
#define RPI_RX_bp 5
#define RPI_TX_bp 4

#define TRANSISTOR_PORT PORTE
#define TRANSISTOR_Q1_bp 0
#define TRANSISTOR_Q2_bp 1
#define TRANSISTOR_Q3_bp 2

#define I2C_PORT PORTA
#define SDA_bp 2
#define SCL_bp 3

#define SPI_PORT PORTA
#define MOSI_bp 4
#define MISO_bp 5
#define SCK_bp 6
#define SS_bp 7
#define CS_PORT PORTB
#define SD_CARD_CS_bp 3

#define SPI1_PORT PORTC
#define SPI1_MOSI_bp 0
#define SPI1_MISO_bp 1
#define SPI1_SCK_bp 2
#define SPI1_CS_bp 3

#define EXT_INT_RTC_PORT PORTB
#define INT2_bp 2

#define EXT_INT_ACC_PORT PORTE
#define INT1_bp 3

#define WS2812B_PORT PORTB
#define WS2812B_bp 4

#define PIEZO_PORT PORTF
#define PIEZO_bp 2

#endif /* BOARD_H_ */