def iter(list):
    res = list()
    for i in list:
        res.append(i**3)
    return res

res = iter([1, 3, 5, 7, 1])
print(res)
print(999)