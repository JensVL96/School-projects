'''
Code written for inf-2200, University of Tromso
'''

import unittest
from cpuElement import CPUElement
from testElement import TestElement

class ALUcontrol(CPUElement):
    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        '''
        Connect ALU control to input sources and controller
        '''
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        
        assert(len(inputSources) == 1), ' ALUcontrol has one inputs'
        assert(len(outputValueNames) == 0), ' ALUcontrol does not have any output'
        assert(len(control) == 2), ' ALUcontrol has two control signal'
        assert(len(outputSignalNames) == 3), ' ALUcontrol has one control output'
        
        self.instruction = inputSources[0][1]
        
        self.ALUop1 = control[0][1]
        self.ALUop0 = control[1][1]
        self.ALUoutSignal1 = outputSignalNames[0]
        self.ALUoutSignal2 = outputSignalNames[1]
        self.ALUoutSignal3 = outputSignalNames[2]

    def writeOutput(self):
        pass

    def setControlSignals(self):
        funct = self.inputValues[self.instruction]
        Op1 = self.controlSignals[self.ALUop1]
        Op2 = self.controlSignals[self.ALUop0]
        funct = funct[-6:]

        self.outputControlSignals[self.ALUoutSignal1] = 0
        self.outputControlSignals[self.ALUoutSignal2] = 0
        self.outputControlSignals[self.ALUoutSignal3] = 0

        if Op1 == 0 and Op2 == 0: #010 -- add on lw, sw and addi
            self.outputControlSignals[self.ALUoutSignal2] = 1
        elif Op2 == 1: #110 -- subtract on beq, bne
            self.outputControlSignals[self.ALUoutSignal1] = 1
            self.outputControlSignals[self.ALUoutSignal2] = 1
        elif Op1 == 1:
            if funct[2:] == '0000': #010 -- add, addu
                self.outputControlSignals[self.ALUoutSignal2] = 1
            if funct[2:] == '0010': #110 -- sub, subu
                self.outputControlSignals[self.ALUoutSignal1] = 1
                self.outputControlSignals[self.ALUoutSignal2] = 1
            if funct[2:] == '0100': #000 -- and 
                pass
            if funct[2:] == '0101': #001 -- or
                self.outputControlSignals[self.ALUoutSignal3] = 1
            if funct[2:] == '0111': #100 -- nor
                self.outputControlSignals[self.ALUoutSignal1] = 1
            if funct[2:] == '1010': #111 -- slt
                self.outputControlSignals[self.ALUoutSignal1] = 1
                self.outputControlSignals[self.ALUoutSignal2] = 1
                self.outputControlSignals[self.ALUoutSignal3] = 1


class TestAluControl(unittest.TestCase):
    def setUp(self):
        self.aluControl = ALUcontrol()
        self.testInput = TestElement()
        self.testOutput = TestElement()
        
        self.testInput.connect(
            [],
            ['dataA'],
            [],
            ['ALUop1', 'Aluop0']
        )

        self.aluControl.connect(
            [(self.testInput, 'dataA')],
            [],
            [(self.testInput, 'memRead'), (self.testInput, 'memWrite')],
            ['ALUoutSignal1', 'ALUoutSignal2', 'ALUoutSignal3']
        )
        
        self.testOutput.connect(
            [],
            [],
            [   (self.aluControl, 'ALUoutSignal1'),
                (self.aluControl, 'ALUoutSignal2'),
                (self.aluControl, 'ALUoutSignal3')],
            []
        )

    def test_correct_behavior(self):
        self.testInput.setOutputValue('dataA', '00000000000000000000000000100101') #or
        self.testInput.setOutputControl('memRead', 1)
        self.testInput.setOutputControl('memWrite', 0)

        self.aluControl.readInput()
        self.aluControl.readControlSignals()
        self.aluControl.setControlSignals()
        self.testOutput.readControlSignals()

        output1 = self.aluControl.outputControlSignals['ALUoutSignal1']
        self.assertEqual(output1, 0)
        output2 = self.aluControl.outputControlSignals['ALUoutSignal2']
        self.assertEqual(output2, 0)
        output3 = self.aluControl.outputControlSignals['ALUoutSignal3']
        self.assertEqual(output3, 1)

        self.testInput.setOutputValue('dataB', '10001100000000000000000000000000') #lw
        self.testInput.setOutputControl('memRead', 0)
        self.testInput.setOutputControl('memWrite', 0)

        self.aluControl.readInput()
        self.aluControl.readControlSignals()
        self.aluControl.setControlSignals()
        self.testOutput.readControlSignals()

        output4 = self.aluControl.outputControlSignals['ALUoutSignal1']
        self.assertEqual(output4, 0)
        output5 = self.aluControl.outputControlSignals['ALUoutSignal2']
        self.assertEqual(output5, 1)
        output6 = self.aluControl.outputControlSignals['ALUoutSignal3']
        self.assertEqual(output6, 0)


        self.testInput.setOutputValue('dataC', '00010000000000000000000000000000') #beq
        self.testInput.setOutputControl('memRead', 0)
        self.testInput.setOutputControl('memWrite', 1)

        self.aluControl.readInput()
        self.aluControl.readControlSignals()
        self.aluControl.setControlSignals()
        self.testOutput.readControlSignals()

        output7 = self.aluControl.outputControlSignals['ALUoutSignal1']
        self.assertEqual(output7, 1)
        output8 = self.aluControl.outputControlSignals['ALUoutSignal2']
        self.assertEqual(output8, 1)
        output9 = self.aluControl.outputControlSignals['ALUoutSignal3']
        self.assertEqual(output9, 0)

if __name__ == '__main__':
    unittest.main()
