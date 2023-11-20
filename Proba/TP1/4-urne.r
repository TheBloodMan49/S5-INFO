urne <- function(k,p,q) {
  vals <- c(rep("Rouge",p),rep("Noire",q))
  return(sample(vals,size=k,replace=F))
}

print(urne(5,2,3))
