from sudoku_reader import Sudoku_reader
import numpy as np
import tkinter as tk
from tkinter import messagebox
from copy import deepcopy

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
        self._set_up_values(self.input)
        self._set_up_elems()
        # self.recursive_solve()

    # Set up the initial values of the squares
    def _set_up_values(self, grid):
        for i in range(self.n_rows):
            for j in range(self.n_cols):
                self.nums[i][j].value = grid[i][j]

    # Recursive method to solve the Sudoku board
    def recursive_solve(self):
        # self.gui.after(100, self.gui.update_gui(self.nums))
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

class SudokuGUI(tk.Tk):
    def __init__(self):
        tk.Tk.__init__(self)

        self.title("Sudoku")
        self.geometry("600x450")
        
        # Initialize 2D list for entry frames
        self.entry_frames = [[None for _ in range(9)] for _ in range(9)]
        self.entries = [[None for _ in range(9)] for _ in range(9)]

        self.fetch_new_board()

    def update_gui(self, grid):
        print("updating")
        for i in range(9):
            for j in range(9):
                value = grid[i][j].value
                # print("new value", value)
                entry = self.entries[i][j]
                entry.delete(0, tk.END)
                entry.insert(0, str(value))
                self.entries[i][j].config(bg='white')

    def fetch_new_board(self):
        new_board = reader.next_board()
    
        # References to the SudokuBoard instance
        self.initial = SudokuBoard(new_board)
        self.board = SudokuBoard(new_board)
        self.solved = SudokuBoard(new_board)

        self.create_widgets()
        self.update_gui(self.board.nums)

    # Create a grid of Entry widgets for the Sudoku board
    def create_widgets(self):
        # Outer frame for padding
        outer_frame = tk.Frame(self, padx=100, pady=70)
        outer_frame.grid(row=0, column=0)
        inner_frame =  tk.Frame(outer_frame, highlightthickness=4, highlightbackground='black')
        inner_frame.grid(row=0, column=0)

        # Create a grid of Entry widgets for the Sudoku board
        self.entries = [[tk.Entry(inner_frame, width=2) for _ in range(9)] for _ in range(9)]

        # Placement of Entry widgets using grid
        for i in range(9):
            for j in range(9):
                entry_frame = tk.Frame(inner_frame, highlightbackground='black', highlightthickness=1)
                entry = tk.Entry(entry_frame, width=2, font=('Arial', 14), justify='center')
                entry.grid(row=0, column=0, sticky='nsew')

                # Determine if it's a thicker line
                thicker_line = (j + 1) % 3 == 0 and j + 1 != 9
                thicker_column = (i + 1) % 3 == 0 and i + 1 != 9

                # Insert values into Entry widgets
                value = self.board.nums[i][j].value
                if value is not None:
                    entry.insert(0, str(value))

                entry_frame.grid(row=i, column=j, padx=1, pady=1, sticky='nsew')
                self.entries[i][j] = entry  # Store a reference to the Entry widget

                # Create a separator frame for the right side
                if thicker_line:
                    separator_frame = tk.Frame(inner_frame, width=2, background='black')
                    separator_frame.grid(row=i, column=j, sticky='nse')

                # Create a separator frame for the bottom side
                if thicker_column:
                    separator_frame = tk.Frame(inner_frame, height=2, background='black')
                    separator_frame.grid(row=i, column=j, sticky='ews')
                    self.entry_frames[i][j] = entry_frame  # Store a reference to the entry frame

                entry.bind('<FocusOut>', lambda event, row=i, col=j: self.on_entry_focus_out(row, col))


        # Frame for the buttons
        button_frame = tk.Frame(outer_frame)
        button_frame.grid(row=0, column=1, padx=10)

        # Buttons to the right of the grid
        solve_button = tk.Button(button_frame, text="Restart", command=self.restart)
        solve_button.grid(row=0, column=0, pady=10)

        clear_button = tk.Button(button_frame, text="Check", command=self.check)
        clear_button.grid(row=1, column=0, pady=10)

        exit_button = tk.Button(button_frame, text="Solve", command=self.solve)
        exit_button.grid(row=2, column=0, pady=10)
                    
        # Button to fetch a new board
        new_board_button = tk.Button(button_frame, text="New", command=self.fetch_new_board)
        new_board_button.grid(row=3, column=0, pady=10)

    # Handler for the FocusOut event
    def on_entry_focus_out(self, row, col):
        # This method is called when an Entry loses focus
        value = self.entries[row][col].get()
        self.board.nums[row][col].value = int(value)
        self.entries[row][col].config(bg='white')
        # Process the entered value or update the board accordingly
        # For example, you can update the self.board.nums[row][col] here
        print(f"Value entered in row {row}, column {col}: {value}")

    # Retrieve values from the Entry widgets
    def solve(self, update=True):
        print("update here:\n\n", update)
        # Attempt to solve the board
        if self.solved.recursive_solve():
            self.solved.recursive_solve()
            # print(self.board.__str__())

            if update:
                print("\n\nyou are set to true\n\n")
                self.update_gui(self.solved.nums)
                messagebox.showinfo("Sudoku", "Solved it for you :P")
        else:
            messagebox.showinfo("Sudoku", "No solution found.")

    def restart(self):
        print("trying to restart")
        self.board = self.initial

        self.update_gui(self.board.nums)

    def check(self):
        self.solve(False)
        print("curr board", np.array(self.board))
        print("teseting", np.array(self.solved))

        # Assuming self.board.nums and self.solved.nums are lists of Square objects
        board_array = np.array([[square.value for square in row] for row in self.board.nums])
        solved_array = np.array([[square.value for square in row] for row in self.solved.nums])

        if np.array_equal(board_array, solved_array):
            messagebox.showinfo("Sudoku", "Congratulations, you solved it!")
            return
        else:
            print("Arrays are not equal.")
            print("Board:", board_array)
            print("Solved:", solved_array)

        flag = True
        # Compare with the finished solution
        for i in range(9):
            for j in range(9):  
                current_value = self.board.nums[i][j].value
                solved_value = self.solved.nums[i][j].value

                # print(f"comparing {current_value} to {solved_value}")

                if current_value != solved_value and current_value != 0:
                    # Highlight the square with the wrong number
                    self.entries[i][j].config(bg='red')
                    flag = False
                    messagebox.showinfo("Sudoku", "There are mistakes")

                else:
                    # Reset the background color if the number is correct
                    self.entries[i][j].config(bg='white')

        if flag:
            messagebox.showinfo("Sudoku", "Great job. No mistakes")

reader = None

if __name__ == "__main__":
    # Test code...
    reader = Sudoku_reader("sudoku_10.csv")

    # Create and run the GUI
    sudoku_gui = SudokuGUI()
    sudoku_gui.mainloop()