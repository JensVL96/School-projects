'''
Code written for inf-2200, University of Tromso
'''

import unittest
from cpuElement import CPUElement
from testElement import TestElement

class AndGate(CPUElement):
    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        '''
        Connect and to input sources and controller
        '''
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        
        assert(len(inputSources) == 0), ' and does not have any inputs'
        assert(len(outputValueNames) == 0), ' and does not have any output'
        assert(len(control) == 2), ' and has two control signals'
        assert(len(outputSignalNames) == 1), ' and has one control output'
        
        self.branch = control[0][1]
        self.Zero = control[1][1]

        self.result = outputSignalNames[0]

    def writeOutput(self):
        pass

    def setControlSignals(self):
        # check if both inputs are equal 1
        a = self.controlSignals[self.branch]
        b = self.controlSignals[self.Zero]

        # 0 & 0 = 0, 0 & 1 = 0, 1 & 0 = 0, 1 & 1 = 1
        if a == 1 and b == 1: 
            self.outputControlSignals[self.result] = 1
        else:
            self.outputControlSignals[self.result] = 0
    
    def printOutput(self):
        pass


class TestAndGate(unittest.TestCase):
    def setUp(self):
        self.andGate = AndGate()
        self.testInput = TestElement()
        self.testOutput = TestElement()
        
        self.testInput.connect(
            [],
            [],
            [],
            ['branch', 'Zero']
        )
        
        self.andGate.connect(
            [],
            [],
            [(self.testInput, 'branch'), (self.testInput, 'Zero')],
            ['result']
        )
        
        self.testOutput.connect(
            [],
            [],
            [(self.andGate, 'result')],
            []
        )
    
    def test_correct_behavior(self):
        self.testInput.setOutputControl('branch', 1)
        self.testInput.setOutputControl('Zero', 0)

        self.andGate.readControlSignals()
        self.andGate.writeOutput()
        self.testOutput.readControlSignals()

        output = self.testOutput.controlSignals['result']
        self.assertEqual(output, 0)


        self.testInput.setOutputControl('branch', 1)
        self.testInput.setOutputControl('Zero', 1)

        self.andGate.readControlSignals()
        self.andGate.setControlSignals()
        self.testOutput.readControlSignals()

        output = self.testOutput.controlSignals['result']
        self.assertEqual(output, 1)

if __name__ == '__main__':
    unittest.main()
