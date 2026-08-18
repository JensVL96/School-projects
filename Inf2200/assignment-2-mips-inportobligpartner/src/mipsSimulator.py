'''
Code written for inf-2200, University of Tromso
'''

from pc import PC
from add import Add
from mux import Mux
from registerFile import RegisterFile
from instructionMemory import InstructionMemory
from dataMemory import DataMemory
from constant import Constant
from randomControl import RandomControl
from control import Control
from alu import ALU
from aluControl import ALUcontrol
from shiftLeft2 import ShiftLeft2
from signExtend import SignExtend
from andGate import AndGate

class MIPSSimulator():
    '''Main class for MIPS pipeline simulator.
    
    Provides the main method tick(), which runs pipeline
    for one clock cycle.
    
    '''
    def __init__(self, memoryFile):
        self.nCycles = 0 # Used to hold number of clock cycles spent executing instructions
        
        self.dataMemory = DataMemory(memoryFile)
        self.instructionMemory = InstructionMemory(memoryFile)
        self.registerFile = RegisterFile()
        
        self.constant3 = Constant(3)
        self.constant4 = Constant(4)
        self.randomControl = RandomControl()
        self.mux = Mux()
        self.adderConst = Add()
        self.pc = PC(0xbfc00000) # hard coded "boot" address

        self.control = Control()
        self.ALU = ALU()
        self.ALUcontrol = ALUcontrol()
        self.andGate = AndGate()
        self.adderALU = Add()
        self.sl2JumpAddress = ShiftLeft2()
        self.sl2SignExtend = ShiftLeft2()
        self.signExtend = SignExtend()

        self.muxRegDst = Mux()
        self.muxALUsrc = Mux()
        self.muxMemToReg = Mux()
        self.muxAndRes = Mux()
        self.muxJump = Mux()
        
        self.elements = [   self.constant4, self.adderConst, 
                            self.instructionMemory, self.control, self.muxRegDst, 
                            self.registerFile, self.signExtend, self.sl2JumpAddress, 
                            self.sl2SignExtend, self.muxALUsrc, self.ALUcontrol, self.ALU, 
                            self.adderALU, self.andGate, self.dataMemory, 
                            self.muxMemToReg, self.muxAndRes, self.muxJump]
        
        self._connectCPUElements()
        
    def _connectCPUElements(self):
        self.constant3.connect(
            [],
            ['constant'],
            [],
            []
        )
        
        self.constant4.connect(
            [],
            ['constant'],
            [],
            []
        )
        
        self.randomControl.connect(
            [],
            [],
            [],
            ['randomSignal']
        )
        
        self.adderConst.connect(
            [(self.pc, 'pcAddress'), (self.constant4, 'constant')],
            ['sum'],
            [],
            []
        )
        
        self.mux.connect(
            [(self.adderConst, 'sum'), (self.constant3, 'constant')],
            ['muxOut'],
            [(self.randomControl, 'randomSignal')],
            []
        )
        
        self.pc.connect(
            [(self.muxJump, 'muxOutJump')],
            ['pcAddress'],
            [],
            []
        )

        self.instructionMemory.connect(
            [(self.pc, 'pcAddress')],
            [   'instruction[31-26]', 'instruction[25-21]', 'instruction[20-16]',
                'instruction[15-11]', 'instruction[25-0]', 'instruction[15-0]',
                'instruction[5-0]'],
            [],
            []
        )

        self.control.connect(
            [(self.instructionMemory, 'instruction[31-26]')],
            [],
            [],
            ['regDst', 'jump', 'branch', 'memRead', 'memToReg', 'ALUop1', 'ALUop0', 'memWrite', 'ALUsrc', 'regWrite']
        )

        self.muxRegDst.connect(
            [   (self.instructionMemory, 'instruction[20-16]'), 
                (self.instructionMemory, 'instruction[15-11]')],
            ['muxOutRegDst'],
            [(self.control, 'regDst')],
            []
        )

        self.registerFile.connect(
            [   (self.instructionMemory, 'instruction[25-21]'), 
                (self.instructionMemory, 'instruction[20-16]'), 
                (self.muxRegDst, 'muxOutRegDst'), 
                (self.muxMemToReg, 'muxOutMemToReg')],
            ['readData1', 'readData2'],
            [(self.control, 'regWrite')],
            []
        )

        self.signExtend.connect(
            [(self.instructionMemory, 'instruction[15-0]')],
            ['signExtendOut'],
            [],
            []
        )

        self.sl2JumpAddress.connect(
            [(self.instructionMemory, 'instruction[25-0]')],
            ['shiftLeftOut'],
            [],
            []
        )

        self.sl2SignExtend.connect(
            [(self.signExtend, 'signExtendOut')],
            ['shiftLeftOut'],
            [],
            []
        )

        self.muxALUsrc.connect(
            [   (self.registerFile, 'readData2'), 
                (self.signExtend, 'signExtendOut')],
            ['muxOutALUsrc'],
            [(self.control, 'ALUsrc')],
            []
        )

        self.ALUcontrol.connect(
            [(self.instructionMemory, 'instruction[5-0]')],
            [],
            [(self.control, 'ALUop1'), (self.control, 'ALUop0')],
            ['ALUoutSignal1', 'ALUoutSignal2', 'ALUoutSignal3']
        )

        self.ALU.connect(
            [(self.registerFile, 'readData1'), (self.muxALUsrc, 'muxOutALUsrc')],
            ['ALUresult'],
            [   (self.ALUcontrol, 'ALUoutSignal1'), (self.ALUcontrol, 'ALUoutSignal2'),
                (self.ALUcontrol, 'ALUoutSignal3')],
            ['Zero']
        )

        self.adderALU.connect(
            [(self.adderConst, 'sum'), (self.sl2SignExtend, 'shiftLeftOut')],
            ['ALUresult'],
            [],
            []
        )

        self.andGate.connect(
            [],
            [],
            [(self.control, 'branch'), (self.ALU, 'Zero')],
            ['andGateOut']
        )

        self.dataMemory.connect(
            [(self.ALU, 'ALUresult'), (self.registerFile, 'readData2')],
            ['readData'],
            [(self.control, 'memWrite'), (self.control, 'memRead')],
            []
        )

        self.muxMemToReg.connect(
            [   (self.dataMemory, 'readData'), 
                (self.ALU, 'ALUresult')],
            ['muxOutMemToReg'],
            [(self.control, 'memToReg')],
            []
        )

        self.muxAndRes.connect(
            [   (self.adderConst, 'sum'), 
                (self.adderALU, 'ALUresult')],
            ['muxOutAndRes'],
            [(self.andGate, 'andGateOut')],
            []
        )

        self.muxJump.connect(
            [   (self.sl2JumpAddress, 'shiftLeftOut'), 
                (self.muxAndRes, 'muxOutAndRes')],
            ['muxOutJump'],
            [(self.control, 'jump')],
            []
        )

    def clockCycles(self):
        '''Returns the number of clock cycles spent executing instructions.'''
        
        return self.nCycles
    
    def dataMemory(self):
        '''Returns dictionary, mapping memory addresses to data, holding
        data memory after instructions have finished executing.'''
        
        return self.dataMemory.memory
    
    def registerFile(self):
        '''Returns dictionary, mapping register numbers to data, holding
        register file after instructions have finished executing.'''
        
        return self.registerFile.register
    
    def printDataMemory(self):
        self.dataMemory.printAll()
    
    def printRegisterFile(self):
        self.registerFile.printAll()
    
    def tick(self):
        '''Execute one clock cycle of pipeline.'''
        
        self.nCycles += 1
        
        # The following is just a small sample implementation
        
        self.pc.writeOutput()
        
        for elem in self.elements:
            elem.readControlSignals()
            elem.readInput()
            elem.writeOutput()
            elem.setControlSignals()
            
        self.pc.readInput()
