'''
Code written for inf-2200, University of Tromso
'''

import unittest
from cpuElement import CPUElement
from testElement import TestElement
import common

class RegisterFile(CPUElement):
    def __init__(self):
        # Dictionary mapping register number to register value
        self.register = {}
        
        # All registers default to 0
        for i in range(0, 32):
            self.register[i] = 0
  
    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        '''
        Check if all the connections are in place
        '''
        assert(len(inputSources) == 4),      'Register file has four inputs'
        assert(len(outputValueNames) == 2),  'Register file has two outputs'
        assert(len(control) == 1),           'Register file has one control signal'
        assert(len(outputSignalNames) == 0), 'Register file does not have any control output'

        # Elements in list inputSources must be tuples of length 2 -> src, name
        #       * first element must be an instance of CPUElement, second element must be string
        self.readReg1 = inputSources[0][1]
        self.readReg2 = inputSources[1][1]
        self.writeReg = inputSources[2][1]
        self.writeData = inputSources[3][1]

        # The element in OutputValueNames must be a string
        self.readDataA = outputValueNames[0] 
        self.readDataB = outputValueNames[1]

        # Elements in list controlSources must be tuples of length 2 -> src, name
        #       * first element must be an instance of CPUElement, second element must be string
        self.controlRegWrite = control[0][1]

        # The element in outputSignalNames must be a string
        
    def printAll(self):
        '''
        Print the name and value in each register.
        '''
        
        # Note that we won't actually use all the registers listed here...
        registerNames = ['$zero', '$at', '$v0', '$v1', '$a0', '$a1', '$a2', '$a3',
                        '$t0', '$t1', '$t2', '$t3', '$t4', '$t5', '$t6', '$t7',
                        '$s0', '$s1', '$s2', '$s3', '$s4', '$s5', '$s6', '$s7',
                        '$t8', '$t9', '$k0', '$k1', '$gp', '$sp', '$fp', '$ra']
        
        print()
        print("Register file")
        print("================")
        for i in range(0, 32):
            print("%s \t=> %s (%s)" % (registerNames[i], common.fromUnsignedWordToSignedWord(self.register[i]), hex(int(self.register[i]))[:-1]))
        print("================")
        print()
        print()

    def writeOutput(self):
        reg1 = int(self.inputValues[self.readReg1], 2)
        reg2 = int(self.inputValues[self.readReg2], 2)
        reg3 = int(self.inputValues[self.writeReg], 2)

        # Input 4 might not register as a tuple since the data comes from a different source
        try:
            reg4 = int(self.inputValues[self.writeData], 2)
        except:
            reg4 = self.inputValues[self.writeData]

        # Check if a control signal is received, if so write the register over the data
        if self.controlSignals[self.controlRegWrite] == 1:
            self.register[reg3] = self.register[reg4]

        # print('registers:', self.register[reg1], self.register[reg2])

        # Set the read input registers as the output values
        self.outputValues[self.readDataA] = self.register[reg1]
        self.outputValues[self.readDataB] = self.register[reg2]


        
class TestRegisterFile(unittest.TestCase):
    def setUp(self):
        '''
        Testing both inputs and outputs together with the register file
        '''
        self.testInput = TestElement()
        self.regFile = RegisterFile()
        self.testOutput = TestElement()

        # Four output signals and one control signal is sent to the register file
        self.testInput.connect(
            [],                                     # No input signals are received
            ['dataA', 'dataB', 'dataC', 'dataD'],   # Four output signals are sent to the register file
            [],                                     # No control signals are received
            ['regWrite'])                           # One control signal is sent to the register file

        # Receives four input signals and one control signal, and sends two output signals
        self.regFile.connect(
            [(self.testInput, 'dataA'), (self.testInput, 'dataB'), (self.testInput, 'dataC'), (self.testInput, 'dataD')],   # Register file has four inputs
            ['dataA', 'dataB'],                                                                                             # Register file has two outputs
            [(self.testInput, 'regWrite')],                                                                                 # Register file has one control signal'
            [])                                                                                                             # Register file does not have any control output

        # Two input signals are received from the register file
        self.testOutput.connect(
            [(self.regFile, 'dataA'), (self.regFile, 'dataB')], # Two input signals are received by the register file
            [],                                                 # No output signals are sent
            [],                                                 # No control input signals are sent
            [])                                                 # No control output signals are sent
        
    
    def test_correct_behavior(self):
        '''
        * Set the values given to the register file
        * Read the input and control signals the register file receives
        * Write the output the register file sends
        * Check the values sent by the register file
        * Print result
        '''

        # The setOutputValue function takes in the name(string) and value(string) of the data
        self.testInput.setOutputValue('dataA', "11111")
        self.testInput.setOutputValue('dataB', "10000")
        self.testInput.setOutputValue('dataC', "10000")
        self.testInput.setOutputValue('dataD', "00000")

        # The setOutputValue function takes in the name(string) and value(int) of the data
        self.testInput.setOutputControl('regWrite', 1)
        
        # Reads and writes the signals for register file
        self.regFile.readInput()
        self.regFile.readControlSignals()
        self.regFile.writeOutput()

        # Check if the outputs received are correct
        self.testOutput.readInput()

        readDataA = self.testOutput.inputValues['dataA']
        readDataB = self.testOutput.inputValues['dataB']

        self.assertEqual(readDataA, 0)
        self.assertEqual(readDataB, 0)
        
        # Prints the registers
        self.regFile.printAll()

if __name__ == '__main__':
    unittest.main()
