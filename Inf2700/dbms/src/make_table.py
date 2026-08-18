from random import randrange
import random
import string

if __name__ == "__main__":
    cmds = ["drop table people;\n"]
    cmds.append("drop table human;\n")

    cmds.append("create table people ( Pid int, gender str(23), weight int );\n")
    cmds.append("create table human ( Pid int, name str(23), age int );\n")

    for i in range(100):
        age = randrange(80)
        letters = string.ascii_lowercase
        name = ''.join(random.choice(letters) for i in range(4))
        gender = randrange(2)
        weight = randrange(120)

        if gender == 1:
            gender = "male"
        else:
            gender = "female"

        cmds.append(f"insert into people values ( {i}, {gender}, {weight} );\n")
        cmds.append(f"insert into human values ( {i}, {name}, {age} );\n")

    cmds.append("select * from people natural join human;\n")
    #cmds.append("quit\n")

    with open("fill.dbcmd", "w") as f:
        f.write("".join(cmds))
