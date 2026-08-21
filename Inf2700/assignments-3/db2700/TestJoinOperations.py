from random import randrange

if __name__ == "__main__":
    cmds = ["drop table tableA;\n", "drop table tableB;\n"]

    cmds.append("create table tableA ( Pid int, age int );\n")
    cmds.append("create table tableB ( Pid int, values int );\n")
    
    for idx in range(100):
        age = randrange(100)
        value = randrange(100)

        cmds.append(f"insert into tableA values ( {idx}, {age} );\n")
        cmds.append(f"insert into tableB values ( {idx}, {value} );\n")

    # SQL queries for join
    cmds.append("select * from tableA natural join tableB;\n")
 
    with open("joinqueries.dbcmd", "w") as f:
        f.write("".join(cmds))
