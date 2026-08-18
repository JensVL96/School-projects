'''
Implements CPU element for Data Memory in MEM stage.

Code written for inf-2200, University of Tromso
'''

import unittest
from cpuElement import CPUElement
from testElement import TestElement
from memory import Memory
import common

class DataMemory(Memory):
    def __init__(self, filename):
        Memory.__init__(self, filename)
        
    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        '''
        Test if all of the connections are in place
        '''
        assert(len(inputSources) == 2), 'Data memory has two inputs'
        assert(len(outputValueNames) == 1), 'Data memory has one output'
        assert(len(control) == 2), 'Data memory has one control signal'
        assert(len(outputSignalNames) == 0), 'Data memory does not have any control output'

        self.address = inputSources[0][1]
        self.writeData = inputSources[1][1]

        self.readData = outputValueNames[0]

        self.CoMemRead = control[0][1]
        self.CoMemWrite = control[1][1]
    
    def writeOutput(self):
        memRead = self.controlSignals[self.CoMemRead]
        memWrite = self.controlSignals[self.CoMemWrite]

        # Interrupt if the memory get a nonvalid address
        try:
            instruction = hex(self.memory[self.inputValues[self.address]])
        except KeyError:
            instruction = 0

        # print('instruction:', instruction, 'memWrite:', memWrite, 'memRead:', memRead)

        # Check the instruction given by the control element
        if memWrite == 1 and memRead == 1:
            raise AssertionError("Data memory element can not receive both control signals")
        elif memWrite == 1:
            instruction = (instruction, self.inputValues[self.writeData])
        elif memRead == 1:
            # print('instruction here:', instruction)
            self.outputValues[self.readData] = instruction

class TestDataMemory(unittest.TestCase):
    def setUp(self):
        self.testInput = TestElement()
        self.dataMem = DataMemory('add.mem')
        self.testOutput = TestElement()

        self.testInput.connect(
            [],
            ['address', 'writeData'],
            [],
            ['memRead', 'memWrite'])

        self.dataMem.connect(
            [(self.testInput, 'address'), (self.testInput, 'writeData')],
            ['readData'],
            [(self.testInput, 'memRead'), (self.testInput, 'memWrite')],
            [])

        self.testOutput.connect(
            [(self.dataMem, 'readData')],
            [],
            [],
            [])
        
    
    def test_correct_behavior(self):
        self.testInput.setOutputValue('address', 0xbfc00008) # 0x64
        self.testInput.setOutputValue('writeData', 0x00000064)
        self.testInput.setOutputControl('memRead', 1)
        self.testInput.setOutputControl('memWrite', 0)

        self.dataMem.readInput()
        self.dataMem.readControlSignals()
        self.dataMem.writeOutput()
        self.testOutput.readInput()

        readData1 = self.testOutput.inputValues['readData']
        self.assertEqual(readData1, '0x64')


        self.testInput.setOutputValue('address', 0xbfc0022c) # break (write)
        self.testInput.setOutputValue('writeData', 0x0000000d)
        self.testInput.setOutputControl('memRead', 0)
        self.testInput.setOutputControl('memWrite', 1)

        self.dataMem.readInput()
        self.dataMem.readControlSignals()
        self.dataMem.writeOutput()
        self.testOutput.readInput()

        # Save the address written to read in the next test
        readData2 = self.testOutput.inputValues['readData']

        self.testInput.setOutputValue('address', readData2) # break (read)
        self.testInput.setOutputValue('writeData', 0x0000000d)
        self.testInput.setOutputControl('memRead', 1)
        self.testInput.setOutputControl('memWrite', 0)

        self.dataMem.readInput()
        self.dataMem.readControlSignals()
        self.dataMem.writeOutput()
        self.testOutput.readInput()

        readData3 = self.testOutput.inputValues['readData']
        self.assertEqual(readData3, 0)

if __name__ == '__main__':
  unittest.main()
