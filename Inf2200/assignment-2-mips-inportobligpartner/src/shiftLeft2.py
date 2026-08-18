'''
Code written for inf-2200, University of Tromso
'''

import unittest
from cpuElement import CPUElement
from testElement import TestElement

class ShiftLeft2(CPUElement):
    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        '''
        Connect shift left 2 to input sources 
        '''
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        
        assert(len(inputSources) == 1), ' ShiftLeft2 has one inputs'
        assert(len(outputValueNames) == 1), ' ShiftLeft2 has one output'
        assert(len(control) == 0), ' ShiftLeft2 does not have any control signals'
        assert(len(outputSignalNames) == 0), ' ShiftLeft2 does not have any control output'
        
        self.instruction = inputSources[0][1]
        
        self.jumpAddress = outputValueNames[0]

    def writeOutput(self):
        # Receives instruction and changes bin code to int
        instruction = int(self.inputValues[self.instruction], 2)
        
        # Bitshifts integer twice to the left, changes it to a binary string and removes '0b'
        instruction = bin(instruction << 2).split('0b')[1]
        instruction = int(instruction)
        
        # Sends the new instruction as output
        self.outputValues[self.jumpAddress] = instruction
    
    def printOutput(self):
        pass


class TestShiftLeft2(unittest.TestCase):
    def setUp(self):
        self.shiftLeft2 = ShiftLeft2()
        self.testInput = TestElement()
        self.testOutput = TestElement()
        
        self.testInput.connect(
            [],
            ['instruction[25-0]'],
            [],
            []
        )
        
        self.shiftLeft2.connect(
            [(self.testInput, 'instruction[25-0]')],
            ['jumpAddress[31-0]'],
            [],
            []
        )
        
        self.testOutput.connect(
            [(self.shiftLeft2, 'jumpAddress[31-0]')],
            [],
            [],
            []
        )
    
    def test_correct_behavior(self):
        self.testInput.setOutputValue('instruction[25-0]', '11111100000000000010000000')

        self.shiftLeft2.readInput()
        self.shiftLeft2.writeOutput()
        self.testOutput.readInput()

        output = self.testOutput.inputValues['jumpAddress[31-0]']
        self.assertEqual(output, 1111110000000000001000000000)

if __name__ == '__main__':
    unittest.main()
