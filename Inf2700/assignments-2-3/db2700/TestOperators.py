from random import randrange

if __name__ == "__main__":
    cmds = ["drop table operators;\n"]

    cmds.append("create table operators ( Pid int, values int );\n")
    
    for i in range(100):
        value = randrange(100)

        cmds.append(f"insert into operators values ( {i}, {value} );\n")
        
    cmds.append("select values from operators where values = 50;\n")
    cmds.append("select values from operators where values < 50;\n")
    cmds.append("select values from operators where values <= 50;\n")
    cmds.append("select values from operators where values > 50;\n")
    cmds.append("select values from operators where values >= 50;\n")
    cmds.append("select values from operators where values >= 50;\n")
    cmds.append("select values from operators where values != 50;\n")

    # cmds.append("quit\n")

    with open("queries.sql", "w") as f:
        f.write("".join(cmds))
