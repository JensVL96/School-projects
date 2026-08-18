from sudoku_reader import Sudoku_reader
import numpy as np

class Board:
    # It is your task to subclass this in order to make it more fit
    # to be a sudoku board

    # Nums parameter is a 2D list, like what the sudoku_reader returns
    def __init__(self, nums):
        self.n_rows = len(nums[0])
        self.n_cols = len(nums)
        self.nums = [[None for _ in range(self.n_rows)] for _ in range(self.n_cols)]

    # Set up the squares on the board (ints into Square objects)
    def _set_up_nums(self):
        for i in range(self.n_rows):
            for j in range(self.n_cols):
                self.nums[i][j] = Square(value=None, row=i, col=j, box=None)

    # Set up links between squares and elements (rows, columns, boxes)
    def _set_up_elems(self):
        for i in range(self.n_rows):
            for j in range(self.n_cols):
                square = self.nums[i][j]
                square.row = Element(squares=self.nums[i])
                square.col = Element(squares=[self.nums[k][j] for k in range(self.n_rows)])
                square.box = Element(squares=[self.nums[i // 3 * 3 + x][j // 3 * 3 + y] for x in range(3) for y in range(3)])

    # Solve the Sudoku board using backtracking
    def solve(self, grid=None, row=0, col=0):
        if(row == self.n_rows - 1 and col == self.n_cols):
            return True
        
        if col == self.n_cols:
            row += 1
            col = 0
        
        if grid[row][col].value > 0:
            return self.solve(grid, row, col + 1)
        
        for num in range(1, self.n_cols + 1):
            if grid[row][col].is_legal(num):
                grid[row][col].set_value(num)

                if self.solve(grid, row, col + 1):
                    return True
                
            grid[row][col].set_value(0)
        return False

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
    
    # Optional print format
    # def __str__(self):
    #     return f"Board with {self.n_rows} rows and {self.n_cols} columns:\n" + \
    #         '\n'.join([str([elem.value for elem in num]) for num in self.nums])

''' Subclass of Board representing a single game of Sudoku '''
class SudokuBoard(Board):
    # Constructor to initialize the Sudoku board
    def __init__(self, input):
        self.input = input
        super().__init__(input)
        self.build_board()

    # Build the Sudoku board with squares, values, and elements
    def build_board(self):
        self._set_up_nums()
        self._set_up_values()
        self._set_up_elems()
        self.recursive_solve()

    # Set up the initial values of the squares
    def _set_up_values(self):
        for i in range(self.n_rows):
            for j in range(self.n_cols):
                self.nums[i][j].value = self.input[i][j]

    # Recursive method to solve the Sudoku board
    def recursive_solve(self):
        return super().solve(self.nums)

''' Class representing a single square on the Sudoku board '''
class Square():
    # Constructor to initialize a square
    def __init__(self, value, row, col, box):
        self.value = value
        self.row = row
        self.col = col
        self.box = box

    # Check if the value is legal in the square's row, column, and box
    def is_legal(self, value):
        return not any((
            self.row.has_value(value),
            self.col.has_value(value),
            self.box.has_value(value)
        ))

    # Set the value in the square if it's legal
    def set_value(self, value):
        self.value = value

''' Class representing a row, column, or box in Sudoku '''
class Element():
    # Constructor to initialize an element with squares
    def __init__(self, squares):
        self.squares = squares

    # Check if any squares in the element have the given value
    def has_value(self, value):
        for square in self.squares:
            if square.value == value:
                return True
        return False

if __name__ == "__main__":
    # Test code...
    reader = Sudoku_reader("sudoku_10.csv")
    initial_board = reader.next_board()

    board = SudokuBoard(initial_board)
    print(" printing the intital board\n", np.array(initial_board))

    print(" Correct solution:\n", np.array(board))