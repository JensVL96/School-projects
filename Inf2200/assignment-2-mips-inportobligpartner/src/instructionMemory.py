'''
Implements CPU element for Instruction Memory in MEM stage.

Code written for inf-2200, University of Tromso
'''

import unittest
import binascii
from cpuElement import CPUElement
from testElement import TestElement
from memory import Memory

class InstructionMemory(Memory):
    def __init__(self, filename):
        Memory.__init__(self, filename)
    
    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        '''
        Test if all of the connections are in place
        '''
        assert(len(inputSources) == 1), 'Instruction memory has one input'
        assert(len(outputValueNames) == 7), 'Instruction memory has seven outputs'
        assert(len(control) == 0), 'Instruction memory does not have any control signals'
        assert(len(outputSignalNames) == 0), 'Instruction memory does not have any control outputs'

        self.readAddr = inputSources[0][1]

        self.instr_31_26 = outputValueNames[0]
        self.instr_25_21 = outputValueNames[0]
        self.instr_20_16 = outputValueNames[0]
        self.instr_15_11 = outputValueNames[0]
        self.instr_25_0 = outputValueNames[0]
        self.instr_15_0 = outputValueNames[0]
        self.instr_5_0 = outputValueNames[0]

    def writeOutput(self):
        # Retrieves the hexidecimal binary code from the memory address
        instruction = self.memory[self.inputValues[self.readAddr]]

        # Converts it into a binary string removing the 0b at thte start
        binary = bin(instruction).split('0b')

        # Checks the length and fills if not long enough
        if len(binary[1]) != 32:
            binary[1] = binary[1].zfill(32)

        # All seperate instructions for the CPU elements
        self.outputValues['instruction[31-26]'] = binary[1][:6]
        self.outputValues['instruction[25-21]'] = binary[1][6:11]
        self.outputValues['instruction[20-16]'] = binary[1][11:16]
        self.outputValues['instruction[15-11]'] = binary[1][16:21]
        self.outputValues['instruction[25-0]'] = binary[1][6:]
        self.outputValues['instruction[15-0]'] = binary[1][16:]
        self.outputValues['instruction[5-0]'] = binary[1][26:]

        # The recipient bitshifts to it's allocated address position

class TestInstructionMemory(unittest.TestCase):
    def setUp(self):
        self.testInput = TestElement()
        self.instMem = InstructionMemory('add.mem')
        self.testOutput = TestElement()
        
        self.testInput.connect(
            [],
            ['dataA'],
            [],
            []
        )
        
        self.instMem.connect(
            [(self.testInput, 'dataA')],
            [   'instruction[31-26]', 'instruction[25-21]', 'instruction[20-16]',
                'instruction[15-11]', 'instruction[25-0]', 'instruction[15-0]',
                'instruction[5-0]'],
            [],
            []
        )
        
        self.testOutput.connect(
            [   (self.instMem, 'instruction[31-26]'), 
                (self.instMem, 'instruction[25-21]'), 
                (self.instMem, 'instruction[20-16]'),
                (self.instMem, 'instruction[15-11]'), 
                (self.instMem, 'instruction[25-0]'), 
                (self.instMem, 'instruction[15-0]'),
                (self.instMem, 'instruction[5-0]')],
            [],
            [],
            []
        )
    
    def test_correct_behavior(self):
        self.testInput.setOutputValue('dataA', 0xbfc00000) #nop
        
        self.instMem.readInput()
        self.instMem.writeOutput()
        self.testOutput.readInput()

        output1 = self.testOutput.inputValues['instruction[31-26]']
        self.assertEqual(output1, '000010')
        output2 = self.testOutput.inputValues['instruction[25-21]']
        self.assertEqual(output2, '11111')
        output3 = self.testOutput.inputValues['instruction[20-16]']
        self.assertEqual(output3, '10000')
        output4 = self.testOutput.inputValues['instruction[15-11]']
        self.assertEqual(output4, '00000')
        output5 = self.testOutput.inputValues['instruction[25-0]']
        self.assertEqual(output5, '11111100000000000010000000')
        output6 = self.testOutput.inputValues['instruction[15-0]']
        self.assertEqual(output6, '0000000010000000')
        output7 = self.testOutput.inputValues['instruction[5-0]']
        self.assertEqual(output7, '000000')

if __name__ == '__main__':
    unittest.main()
