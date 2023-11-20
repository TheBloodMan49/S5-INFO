freq <- function(n) {
  vals <- sample(1:6,size=n,replace=T)
  nb <- sum(vals==5)
  return (nb / n)
}

print(freq(10000))
