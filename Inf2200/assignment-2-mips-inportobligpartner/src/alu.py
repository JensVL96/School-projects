import unittest
from cpuElement import CPUElement
from testElement import TestElement

class ALU(CPUElement):
    """
    control : 4 bit control/selector vector.
    inputSources[0][1]: operator 1. 32bits
    inputSources[1][1]: operator 2. 32bits
    outputValueNames[0]: ALU result. 32bits
    outputSignalNames: zero detector. ``1`` when out is 0. 
    =============   =========   =======================
    ALU control     OP name     Function
    =============   =========   =======================
    10 0100         and         Binary AND                  Three register operands; bit-by-bit AND
    10 0101         or          Binary OR                   Three register operands; bit-by-bit OR
    10 0000         add         Add                         Three operands; overflow detected
    10 0010         sub         Subtract                    Three operands; overflow detected
    10 1010         slt         Set on less than            Compare less than; two's complement
    10 0111         nor         Binary NOR                  Three register operands; bit-by-bit NOR

    10 0011         lw          Load word                   Word from memory to register
    10 1011         sw          Store word                  Word from register to memory
    00 1111         lui         Load upper immediate        Loads constant in upper 16 bits

    00 0100         beq         Branch on equal             Equal test; PC-relative branch   
    00 0101         bne         branch on not equal         Not equal test; PC-relative

    10 0001         addu        Add unsigned                Three operands; overflow undetected
    00 1000         addi        Add immediate               + constant; overflow detected
    00 1001         addiu       Add immediate unsigned      + constant; overflow undetected
    10 0011         subu        Subtract unsigned           Three operands; overflow undetected

    00 1101         break       Break execution
    00 0010         j           Jump                        Jump to target address
    =============   =========   =======================
    """

    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        '''
        Connect ALU to input sources and controller
        '''
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        
        assert(len(inputSources) == 2), 'ALU should have two inputs'
        assert(len(outputValueNames) == 1), 'ALU has two output'
        assert(len(control) == 3), 'ALU has three control signals'
        assert(len(outputSignalNames) == 1), 'ALU has one control output'
        
        self.readData = inputSources[0][1]
        self.readMuxData = inputSources[1][1]

        self.ALUresult = outputValueNames[0]

        self.ALUinSignal1 = control[0][1]
        self.ALUinSignal2 = control[1][1]
        self.ALUinSignal3 = control[2][1]

        self.Zero = outputSignalNames[0]

    def writeOutput(self):
        ALUop1 = self.controlSignals[self.ALUinSignal1]
        ALUop2 = self.controlSignals[self.ALUinSignal2]
        ALUop3 = self.controlSignals[self.ALUinSignal3]
        x = self.inputValues[self.readData]
        y = self.inputValues[self.readMuxData]

        #print('ALUop1:', ALUop1, 'ALUop2:', ALUop2, 'ALUop3:', ALUop3, 'x:', x, 'y:', y)

        sum = 0

        if ALUop1 == 0 and ALUop2 == 1 and ALUop3 == 0: #add
            sum = x + y
        elif ALUop1 == 1 and ALUop2 == 1 and ALUop3 == 0: #sub
            sum = x - y
        elif ALUop1 == 0 and ALUop2 == 0 and ALUop3 == 0: #bitwise and
            sum = x and y
        elif ALUop1 == 0 and ALUop2 == 0 and ALUop3 == 1: #bitwise or
            sum = x | y
        elif ALUop1 == 1 and ALUop2 == 0 and ALUop3 == 0: #bitwise nor
            sum = ~(x | y)
        elif ALUop1 == 1 and ALUop2 == 1 and ALUop3 == 1: #slt
            sum = x < y

        if sum == 1:
            self.outputControlSignals[self.Zero] = 0
        else:
            self.outputControlSignals[self.Zero] = 1

        # print('sum: ', sum, type(sum))
        self.outputValues[self.ALUresult] = sum

class TestAlu(unittest.TestCase):
    def setUp(self):
        self.alu = ALU()
        self.testInput = TestElement()
        self.testOutput = TestElement()
        
        self.testInput.connect(
            [],
            ['dataA', 'dataB'],
            [],
            ['ALUControl1', 'ALUControl2', 'ALUControl3']
        )
        
        self.alu.connect(
            [(self.testInput, 'dataA'), (self.testInput, 'dataB')],
            ['ALUresult'],
            [   (self.testInput, 'ALUControl1'),
                (self.testInput, 'ALUControl2'),
                (self.testInput, 'ALUControl3')],
            ['Zero']
        )
        
        self.testOutput.connect(
            [(self.alu, 'ALUresult')],
            [],
            [(self.alu, 'Zero')],
            []
        )
    
    def test_correct_behavior(self):
        self.testInput.setOutputValue('dataA', 1)
        self.testInput.setOutputValue('dataB', 0)
        self.testInput.setOutputControl('ALUControl1', 0) #|
        self.testInput.setOutputControl('ALUControl2', 1) #| add
        self.testInput.setOutputControl('ALUControl3', 0) #|
        
        self.alu.readInput()
        self.alu.readControlSignals()
        self.alu.writeOutput()
        self.testOutput.readInput()
        self.testOutput.readControlSignals()

        output1 = self.testOutput.inputValues['ALUresult']
        self.assertEqual(output1, 1)
        output2 = self.testOutput.controlSignals['Zero']
        self.assertEqual(output2, 0)


        self.testInput.setOutputValue('dataA', 1)
        self.testInput.setOutputValue('dataB', 0)
        self.testInput.setOutputControl('ALUControl1', 0) #|
        self.testInput.setOutputControl('ALUControl2', 0) #| and
        self.testInput.setOutputControl('ALUControl3', 0) #|
        
        self.alu.readInput()
        self.alu.readControlSignals()
        self.alu.writeOutput()
        self.testOutput.readInput()
        self.testOutput.readControlSignals()

        output1 = self.testOutput.inputValues['ALUresult']
        self.assertEqual(output1, 0)
        output2 = self.testOutput.controlSignals['Zero']
        self.assertEqual(output2, 1)

if __name__ == '__main__':
    unittest.main()
