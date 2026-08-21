from sudoku_reader import Sudoku_reader
import numpy as np

class Board:
    # It is your task to subclass this in order to make it more fit
    # to be a sudoku board

    # Nums parameter is a 2D list, like what the sudoku_reader returns
    def __init__(self, nums):
        self.n_rows = len(nums[0])
        self.n_cols = len(nums)
        print("input:", nums)
        self.nums = [[None for _ in range(self.n_rows)] for _ in range(self.n_cols)]
        print("whatchu doing?", self.nums)

    # Set up the squares on the board (ints into Square objects)
    def _set_up_nums(self):
        for i in range(self.n_rows):
            for j in range(self.n_cols):
                self.nums[i][j] = Square(value=None, row=i, col=j, box=None)
                # row_elem = Element(self.nums[i])
                # col_elem = Element([self.nums[k][j] for k in range(self.n_rows)])
                # box_elem = Element([self.nums[i // 3 * 3 + x][j // 3 * 3 + y] for x in range(3) for y in range(3)])
                # self.nums[i][j] = Square(value=None, row=row_elem, col=col_elem, box=box_elem)
        print("size of setup:", len(self.nums) * len(self.nums[0]))

    def _set_up_elems(self):
        # You should set up links between your squares and elements
        # (rows, columns, boxes)
        for i in range(self.n_rows):
            for j in range(self.n_cols):
                print("default for setting elements:", self.nums[i][j].value, end="")
                square = self.nums[i][j]
                square.row = Element(squares=self.nums[i])
                square.col = Element(squares=[self.nums[k][j] for k in range(self.n_rows)])
                square.box = Element(squares=[self.nums[i // 3 * 3 + x][j // 3 * 3 + y] for x in range(3) for y in range(3)])
        # for i in range(self.n_rows):
        #     for j in range(self.n_cols):
        #         square = self.nums[i][j]
                # square.row.squares.append(square)
                # square.col.squares.append(square)
                # square.box.squares.append(square)
                # square.row = self.nums[i]
                # square.col = [self.nums[k][j] for k in range(self.n_rows)]
                # square.box = [self.nums[i // 3 * 3 + x][j // 3 * 3 + y] for x in range(3) for y in range(3)]



    """
    Sudoku algorithms
    The simplest sudoku solving algorithm is the brute-force method. Solving a sudoku using brute force
    is done as follows:
    1. Starting at the first square, find the lowest legal value between 1 and 9
    2. Go to the next square and do the same
    a. If legal value is found, go to the next square and repeat step 2
    b. If NO values are legal, go back to the previous square and find the next legal value
    3. The solution is found when all squares have gotten a number and no row, column or box
    contains the same value twice or more
    Using this method, you can also find multiple solutions to a single sudoku board – simply keep going
    once you find a solution, roll back and try more values."""
    # def solve(self, row=0, col=0):
    #     stack = []

    #     while stack or (row < self.n_rows and col < self.n_cols):
    #         if self.nums[row][col].value != 0:
    #             # Move to the next cell
    #             print(f"Moving to next cell: row = {row}, col = {col}")
    #             row, col = (row + 1 if col == self.n_cols - 1 else row, (col + 1) % self.n_cols)
    #             continue

    #         for num in range(1, 10):
    #             if self.nums[row][col].is_legal(num):
    #                 self.nums[row][col].set_value(num)
    #                 stack.append((row, col))  # Save the current cell
    #                 print(f"Found a new number: {num} at row = {row}, col = {col}")
    #                 row, col = (row + 1 if col == self.n_cols - 1 else row, (col + 1) % self.n_cols)  # Move to the next cell
    #                 break
    #             print(f"Backtracking: len(stack) = {len(stack)}")

    #         else:
    #             # Backtrack if no valid value is found
    #             self.nums[row][col].set_value(0)
    #             row, col = stack.pop()  # Backtrack to the previous state
    #             print(f"Backtracking to: row = {row}, col = {col}")

    #     return True

    def solve(self, grid=0, row=0, col=0):
        N = 9
        if(row == N - 1 and col == N):
            return True
        
        if col == N:
            row += 1
            col = 0
        
        if grid[row][col].value > 0:
            return self.solve(grid, row, col + 1)
        
        for num in range(1, N + 1, 1):
            if grid[row][col].is_legal(num):
                grid[row][col].set_value(num)

                if self.solve(grid, row, col + 1):
                    return True
                
            grid[row][col].set_value(0)
        return False

    # def solve(self, row=0, col=0):
    #     # Your solving algorithm goes here!
    #     if row == self.n_rows:
    #         print("\n\nok, youre done\n\n")
    #         return True # Solved the board
        
        
    #     # for i in range(self.n_rows):
    #     #     for j in range(self.n_cols):
    #     #         print(f"checking square: [{i}] [{j}]")
    #     #         print(self.nums[i][j].value)
    #     #         print(self.nums[i][j].row)
    #     #         print(self.nums[i][j].col)
    #     #         print(self.nums[i][j].box)

    #     print("start solving with input:", self.__str__())
    #     next_row, next_col = (row, col + 1) if col < self.n_cols - 1 else (row + 1, 0)
        
    #     if self.nums[row][col].value != 0:
    #         print("\n\nHey, you arent finished yet. Next row!\n\n")
    #         print("value is: ", self.nums[row][col].value)
    #         return self.solve(next_row, next_col)

    #     for num in range (1, 10):
    #         print(f"checking square: [{row}][{col}]", self.nums[row][col].value, "for", num)
    #         if self.nums[row][col].is_legal(num):
    #             print("checked value")
    #             self.nums[row][col].set_value(num)
    #             print("set value: ", num)
    #             print(self.__str__())
    #             if self.solve():
    #                 return True
    #             self.nums[row][col].set_value(0)
                
    #     return False

    # Makes it possible to print a board in a sensible format
    def __str__(self):
        r = "Board with " + str(self.n_rows) + " rows and " + str(self.n_cols) + " columns:\n"
        r += "[["
        for num in self.nums:
            for elem in num:
                r += elem.value.__str__() + ", "
            r = r[:-2] + "]" + "\n ["
        r = r[:-3] + "]"
        return r

"""SudokuBoard
This must be a subclass of the Board class from the precode, and objects of the class represent a
single game of sudoku. The class should contain functionality allowing you to build a board out of
squares and elements (see below), and a method that solves the board given the initial numbers.
"""
class SudokuBoard(Board):
    def __init__(self, input):
        self.input = input
        super().__init__(input)
        print("input in sudoku: ", input)
        self.build_board()

    # Initialize squares, rows, columns, and boxes
    # Create instances of Square, Row, Column, and Box, and set references accordingly
    def build_board(self):
        self._set_up_nums()
        self._set_up_values()
        self._set_up_elems()
        self.recurssive_solve()

    def _set_up_values(self):
        for i in range(self.n_rows):
            for j in range(self.n_cols):
                # print("set value: ", self.input[i][j], f"at position: [{i}][{j}]")
                self.nums[i][j].value = self.input[i][j]

    # Implement a Sudoku-solving algorithm using backtracking or other methods
    def recurssive_solve(self):
        return super().solve(self.nums)
"""Square
This class represents a single square on a sudoku board – thus, a SudokuBoard should contain
exactly 81 (i.e., 9*9) squares. A square should be able to store its number, as well as references to
the row, column and box it is part of. The Square class should contain a method to check whether a
number is legal, checking whether it exists in its row, column or box. It should also contain a method
that sets the number in the square if the value is legal.
"""
class Square():
    def __init__(self, value, row, col, box):
        self.value = value
        self.row = row
        self.col = col
        self.box = box

    #Check if the value is legal in the square's row, column and box
    def is_legal(self, value):
        # print(f"Checking if {value} is legal in square: [{self.row}] [{self.col}] [{self.box}]")
       
        #if any(value in _list in connections if _list in (row, col, box))
        return not any((
            self.row.has_value(value),
            self.col.has_value(value),
            self.box.has_value(value)
        ))
        """row = Element(self.row).has_value(value)
        col = Element(self.col).has_value(value)
        box = Element(self.box).has_value(value)

        if any((row, col, box)):
           return True
        return False"""

    # Set the value in the square if it's legal
    def set_value(self, value):
        self.value = value
        # print(f"\nadding {value}\n")

"""Element
The Element class represents a row, column or box in your game, thus it should have references to 9
objects of Square, as all rows, columns and boxes keep track of 9 squares. It should have
functionality to check whether any square it contains has a given value – you need to call this
function in Square when checking if a value is legal.
In addition to the code, you must hand in a class diagram showing the relationships between the
classes in your program. Remember to include inheritance arrows. We are not strict about diagrams
conforming to the UML standard, but as a minimum your diagram should clearly show how the
classes in your program are related to each other."""
class Element():
    def __init__(self, squares=None):
        self.squares = squares

    # def __iter__(self):
    #     return iter(self.squares)

    # Check if any squares in the element has the given value
    def has_value(self, value):
        # print(f"does {self.squares} have the value?")
        for square in self.squares:
            # print("checking if ", value, "is equal", square.value)
            if square.value == value:
                return True
        return False

if __name__ == "__main__":
    # Test code...
    reader = Sudoku_reader("sudoku_10.csv")
    initial_board = reader.next_board()

    board = SudokuBoard(initial_board)
    print(" printing the intital board", initial_board)

    print("are you the sinner", np.array(board))