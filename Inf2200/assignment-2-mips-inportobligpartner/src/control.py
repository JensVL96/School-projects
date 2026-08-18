'''
Code written for inf-2200, University of Tromso
'''

import unittest
from cpuElement import CPUElement
from testElement import TestElement

class Control(CPUElement):
    def connect(self, inputSources, outputValueNames, control, outputSignalNames):
        '''
        Connect Control to input sources
        '''
        CPUElement.connect(self, inputSources, outputValueNames, control, outputSignalNames)
        
        assert(len(inputSources) == 1), ' Control has one input'
        assert(len(outputValueNames) == 0), ' Control does not have any output'
        assert(len(control) == 0), ' Control does not have any control signal'
        assert(len(outputSignalNames) == 10), ' Control has ten control outputs'
        
        self.instruction = inputSources[0][1]

        self.regDst = outputSignalNames[0]
        self.branch = outputSignalNames[1]
        self.memRead = outputSignalNames[2]
        self.memToReg = outputSignalNames[3]
        self.ALUop1 = outputSignalNames[4]
        self.ALUop0 = outputSignalNames[5]
        self.memWrite = outputSignalNames[6]
        self.ALUsrc = outputSignalNames[7]
        self.regWrite = outputSignalNames[8]
        self.jump = outputSignalNames[9]

    def writeOutput(self):
        pass

    def setControlSignals(self):
        """
        * Retrieve the operation code from the instruction input
        * Default all output signals to 0
        * Check for signal type
            - if found change control output signal to 1

        opcode -- 6bit opcode field from instruction
        RegDst, ALUsrc, memtoReg -- 1bit signals to control multiplexors
        RegWrite, memRead, memWrite -- 1bit signals to control reads and writes in registers and memory
        Branch -- 1bit signal to determining whether to possibly branch
        ALUop -- 2bit control signal for the ALU

        R-format    (rd,rs,rt): add, addu, sub, subu, and, or, nor, slt
        I-format:   addi (rt,rs,imm), addiu(rt,rs,imm), lui(rt,imm), sw(rt,offset(rs)), 
                    lw(rt,offset(rs)), beq(rs,rt,offset), bne(rs,rt,offset), 
                    break(not core instruction)
        J-format:   jump(target)

        R-type instruction
        field:  |       O       |   rs  |   rt  |   rd  |   shamt   |   funt    |
        bit pos:      31:26       25:21   20:16   15:11     10:6        5:0

        Load or store instruction
        field:  |   35 or 43    |   rs  |   rt  |           address             |
        bit pos:     31:26        25:21   20:16              15:0

        branch instruction
        field:  |       4       |   rs  |   rt  |           address             |
        bit pos:      31:26       25:21   20:16              15:0
        """
        opCode = self.inputValues[self.instruction]
        opCode = opCode[:6] # Splits the instruction so only the first 6 bits remain

        self.outputControlSignals[self.regDst] = 0
        self.outputControlSignals[self.ALUsrc] = 0
        self.outputControlSignals[self.memToReg] = 0
        self.outputControlSignals[self.regWrite] = 0
        self.outputControlSignals[self.memRead] = 0
        self.outputControlSignals[self.memWrite] = 0
        self.outputControlSignals[self.branch] = 0
        self.outputControlSignals[self.ALUop1] = 0
        self.outputControlSignals[self.ALUop0] = 0
        self.outputControlSignals[self.jump] == 0

        if opCode == '000000':  #R-format: add, addu, sub, subu, and, or, nor, slt
            self.outputControlSignals[self.regDst] = 1
            self.outputControlSignals[self.regWrite] = 1
            self.outputControlSignals[self.ALUop1] = 1

        elif opCode == '100011': #lw
            self.outputControlSignals[self.ALUsrc] = 1
            self.outputControlSignals[self.memToReg] = 1
            self.outputControlSignals[self.regWrite] = 1
            self.outputControlSignals[self.memRead] = 1

        elif opCode == '101011': #sw
            self.outputControlSignals[self.ALUsrc] = 1
            self.outputControlSignals[self.memWrite] = 1

        elif opCode == '001000': #addi
            self.outputControlSignals[self.ALUsrc] = 1
            self.outputControlSignals[self.regWrite] = 1
            self.outputControlSignals[self.ALUop1] = 1
            self.outputControlSignals[self.ALUop0] = 1

        elif opCode == '001001': #addiu
            self.outputControlSignals[self.ALUsrc] = 1
            self.outputControlSignals[self.regWrite] = 1
            self.outputControlSignals[self.ALUop1] = 1
            self.outputControlSignals[self.ALUop0] = 1

        elif opCode == '000100': #beq
            self.outputControlSignals[self.branch] = 1
            self.outputControlSignals[self.ALUop0] = 1

        elif opCode == '000101': #bne
            self.outputControlSignals[self.branch] = 1
            self.outputControlSignals[self.ALUop0] = 1

        elif opCode == '001111': #lui
            # The immediate value is shifted left 16 bits and stored in the register. The lower 16 bits are zeroes.
            # self.outputControlSignals[self.regWrite] = 1
            pass

        elif opCode == '000010': #jump
            self.outputControlSignals[self.jump] = 1

        elif opCode == '001101': #break
            # Since the BREAK instruction takes no operands, it doesn't make use of bits 6
            pass


class TestControl(unittest.TestCase):
    def setUp(self):
        self.control = Control()
        self.testInput = TestElement()
        self.testOutput = TestElement()
        
        self.testInput.connect(
            [],
            ['dataA'],
            [],
            []
        )
        
        self.control.connect(
            [(self.testInput, 'dataA')],
            [],
            [],
            [   'regDst', 'branch', 'memRead', 'memToReg', 'ALUop1', 
                'ALUop0', 'memWrite', 'ALUsrc', 'regWrite', 'jump']
        )
        
        self.testOutput.connect(
            [],
            [],
            [   (self.control, 'regDst'), (self.control, 'branch'), 
                (self.control, 'memRead'), (self.control, 'memToReg'), 
                (self.control, 'ALUop1'), (self.control, 'ALUop0'), 
                (self.control, 'memWrite'), (self.control, 'ALUsrc'), 
                (self.control, 'regWrite'), (self.control, 'jump')],
            []
        )
    
    def test_correct_behavior(self):
        self.testInput.setOutputValue('dataA', '00000000000000000000000000100000') #add
        
        self.control.readInput()
        self.control.readControlSignals()
        self.control.setControlSignals()
        self.testOutput.readControlSignals()

        output1 = self.control.outputControlSignals['regDst']
        self.assertEqual(output1, 1)
        output2 = self.control.outputControlSignals['ALUsrc']
        self.assertEqual(output2, 0)
        output3 = self.control.outputControlSignals['memToReg']
        self.assertEqual(output3, 0)
        output4 = self.control.outputControlSignals['regWrite']
        self.assertEqual(output4, 1)
        output5 = self.control.outputControlSignals['memRead']
        self.assertEqual(output5, 0)
        output6 = self.control.outputControlSignals['memWrite']
        self.assertEqual(output6, 0)
        output7 = self.control.outputControlSignals['branch']
        self.assertEqual(output7, 0)
        output8 = self.control.outputControlSignals['ALUop1']
        self.assertEqual(output8, 1)
        output9 = self.control.outputControlSignals['ALUop0']
        self.assertEqual(output9, 0)
        output0 = self.control.outputControlSignals['jump']
        self.assertEqual(output0, 0)
        
        self.testInput.setOutputValue('dataA', '10001100000000000000000000000000') #lw

        self.control.readInput()
        self.control.readControlSignals()
        self.control.setControlSignals()
        self.testOutput.readControlSignals()

        output1 = self.control.outputControlSignals['regDst']
        self.assertEqual(output1, 0)
        output2 = self.control.outputControlSignals['ALUsrc']
        self.assertEqual(output2, 1)
        output3 = self.control.outputControlSignals['memToReg']
        self.assertEqual(output3, 1)
        output4 = self.control.outputControlSignals['regWrite']
        self.assertEqual(output4, 1)
        output5 = self.control.outputControlSignals['memRead']
        self.assertEqual(output5, 1)
        output6 = self.control.outputControlSignals['memWrite']
        self.assertEqual(output6, 0)
        output7 = self.control.outputControlSignals['branch']
        self.assertEqual(output7, 0)
        output8 = self.control.outputControlSignals['ALUop1']
        self.assertEqual(output8, 0)
        output9 = self.control.outputControlSignals['ALUop0']
        self.assertEqual(output9, 0)
        output0 = self.control.outputControlSignals['jump']
        self.assertEqual(output0, 0)

        self.testInput.setOutputValue('dataA', '10101100000000000000000000000000') #sw

        self.control.readInput()
        self.control.readControlSignals()
        self.control.setControlSignals()
        self.testOutput.readControlSignals()

        output1 = self.control.outputControlSignals['regDst']
        self.assertEqual(output1, 0)
        output2 = self.control.outputControlSignals['ALUsrc']
        self.assertEqual(output2, 1)
        output3 = self.control.outputControlSignals['memToReg']
        self.assertEqual(output3, 0)
        output4 = self.control.outputControlSignals['regWrite']
        self.assertEqual(output4, 0)
        output5 = self.control.outputControlSignals['memRead']
        self.assertEqual(output5, 0)
        output6 = self.control.outputControlSignals['memWrite']
        self.assertEqual(output6, 1)
        output7 = self.control.outputControlSignals['branch']
        self.assertEqual(output7, 0)
        output8 = self.control.outputControlSignals['ALUop1']
        self.assertEqual(output8, 0)
        output9 = self.control.outputControlSignals['ALUop0']
        self.assertEqual(output9, 0)
        output0 = self.control.outputControlSignals['jump']
        self.assertEqual(output0, 0)

        self.testInput.setOutputValue('dataA', '00100000000000000000000000000000') #addi

        self.control.readInput()
        self.control.readControlSignals()
        self.control.setControlSignals()
        self.testOutput.readControlSignals()

        output1 = self.control.outputControlSignals['regDst']
        self.assertEqual(output1, 0)
        output2 = self.control.outputControlSignals['ALUsrc']
        self.assertEqual(output2, 1)
        output3 = self.control.outputControlSignals['memToReg']
        self.assertEqual(output3, 0)
        output4 = self.control.outputControlSignals['regWrite']
        self.assertEqual(output4, 1)
        output5 = self.control.outputControlSignals['memRead']
        self.assertEqual(output5, 0)
        output6 = self.control.outputControlSignals['memWrite']
        self.assertEqual(output6, 0)
        output7 = self.control.outputControlSignals['branch']
        self.assertEqual(output7, 0)
        output8 = self.control.outputControlSignals['ALUop1']
        self.assertEqual(output8, 1)
        output9 = self.control.outputControlSignals['ALUop0']
        self.assertEqual(output9, 1)
        output0 = self.control.outputControlSignals['jump']
        self.assertEqual(output0, 0)

        self.testInput.setOutputValue('dataA', '00010000000000000000000000000000') #beq

        self.control.readInput()
        self.control.readControlSignals()
        self.control.setControlSignals()
        self.testOutput.readControlSignals()

        output1 = self.control.outputControlSignals['regDst']
        self.assertEqual(output1, 0)
        output2 = self.control.outputControlSignals['ALUsrc']
        self.assertEqual(output2, 0)
        output3 = self.control.outputControlSignals['memToReg']
        self.assertEqual(output3, 0)
        output4 = self.control.outputControlSignals['regWrite']
        self.assertEqual(output4, 0)
        output5 = self.control.outputControlSignals['memRead']
        self.assertEqual(output5, 0)
        output6 = self.control.outputControlSignals['memWrite']
        self.assertEqual(output6, 0)
        output7 = self.control.outputControlSignals['branch']
        self.assertEqual(output7, 1)
        output8 = self.control.outputControlSignals['ALUop1']
        self.assertEqual(output8, 0)
        output9 = self.control.outputControlSignals['ALUop0']
        self.assertEqual(output9, 1)
        output0 = self.control.outputControlSignals['jump']
        self.assertEqual(output0, 0)


if __name__ == '__main__':
    unittest.main()
