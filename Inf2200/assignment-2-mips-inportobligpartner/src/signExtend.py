'''
Code written for inf-2200, University of Tromso
'''

import unittest
from cpuElement import CPUElement
from testElement import TestElement

class SignExtend(CPUElement):
    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        '''
        Connect sign extend to input sources
        '''
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        
        assert(len(inputSources) == 1), ' Sign extend has one input'
        assert(len(outputValueNames) == 1), ' Sign extend has one output'
        assert(len(control) == 0), ' Sign extend does not have any control signal'
        assert(len(outputSignalNames) == 0), ' Sign extend does not have any control output'
        
        self.instructionIn = inputSources[0][1]
        self.InstructionOut = outputValueNames[0]

    def writeOutput(self):
        # Receives the instruction
        instruction = self.inputValues[self.instructionIn]

        # Finds the highest bit
        highestBit = instruction[:1]

        # Pads the instruction with the highest bit until desired length
        instruction = instruction.rjust(32, highestBit)

        # Sends padded instruction as output
        self.outputValues['signExtendOut'] = instruction

    def printOutput(self):
        pass


class TestSignExtend(unittest.TestCase):
    def setUp(self):
        self.signExtend = SignExtend()
        self.testInput = TestElement()
        self.testOutput = TestElement()
        
        self.testInput.connect(
            [],
            ['instruction[15-0]'],
            [],
            []
        )
        
        self.signExtend.connect(
            [(self.testInput, 'instruction[15-0]')],
            ['signExtendOut'],
            [],
            []
        )
        
        self.testOutput.connect(
            [(self.signExtend, 'signExtendOut')],
            [],
            [],
            []
        )
    
    def test_correct_behavior(self):
        self.testInput.setOutputValue('instruction[15-0]', '0000000010000000')

        self.signExtend.readInput()
        self.signExtend.writeOutput()
        self.testOutput.readInput()

        output = self.testOutput.inputValues['signExtendOut']
        self.assertEqual(output, '00000000000000000000000010000000')


        self.testInput.setOutputValue('instruction[15-0]', '1000000010000000')

        self.signExtend.readInput()
        self.signExtend.writeOutput()
        self.testOutput.readInput()

        output = self.testOutput.inputValues['signExtendOut']
        self.assertEqual(output, '11111111111111111000000010000000')

if __name__ == '__main__':
    unittest.main()
