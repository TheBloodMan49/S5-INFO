from random import randint


def initialiserAleatoire(n, vmin, vmax):
    return [randint(vmin,vmax) for _ in range(n)]

def tri_selection(tab):
    for i in range(len(tab)):
        min = i
        for j in range(i+1, len(tab)):
            if tab[j] < tab[min]:
                min = j
        tmp = tab[i]
        tab[i] = tab[min]
        tab[min] = tmp


def main():
    val = initialiserAleatoire(10, 1, 10)
    print(val)
    tri_selection(val)
    print(val)


if __name__ == "__main__":
    main()