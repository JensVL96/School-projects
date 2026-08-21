from random import randrange
import random
import string

if __name__ == "__main__":
    cmds = ["drop table people;\n"]
    cmds.append("drop table human;\n")

    cmds.append("create table people ( Pid int, weight int );\n")
    cmds.append("create table human ( Pid int, age int );\n")

    for i in range(100):
        age = randrange(80)
        weight = randrange(120)

        cmds.append(f"insert into people values ( {i}, {weight} );\n")
        cmds.append(f"insert into human values ( {i}, {age} );\n")

    cmds.append("select * from people natural join human;\n")
    cmds.append("quit\n")

    with open("fill.dbcmd", "w") as f:
        f.write("".join(cmds))
