##Install the library
library(igraph)

##Small-World Networks
sample_smallworld(1,20,2,0.05) -> smallworld
degree.distribution(smallworld)*20 -> smallworld_degree

#Plot the Diagram
plot(smallworld,edge.color = 'black',vertex.label.cex =0.5,edge.width=sqrt(E(smallworld)$weight/800),
     layout = layout.fruchterman.reingold)

#Degree Table
barplot(smallworld_degree, main="Small-World", xlab = "Degree", ylab ="Number of Agents")

##Scale-Free Networks
barabasi.game(20, directed = FALSE) -> scalefree
degree.distribution(scalefree)*20 -> scalefree_degree

#Append to fit two networks
append(smallworld_degree,0) -> smallworld_degree1
append(smallworld_degree1,0) -> smallworld_degree

#Plot the Diagram
plot(scalefree,edge.color = 'black',vertex.label.cex =0.5,edge.width=sqrt(E(scalefree)$weight/800),
     layout = layout.fruchterman.reingold)

#Degree Table
barplot(scalefree_degree, main="Scale-Free", xlab = "Degree", ylab ="Number of Agents")

##Histogram
matrix(c(smallworld_degree,scalefree_degree), nrow = 2, ncol = 8, byrow = TRUE)
matrix(c(smallworld_degree,scalefree_degree), nrow = 2, ncol = 8, byrow = TRUE) -> counts
barplot(counts, main="Histogram",
        xlab="Degree", ylab = "Number of Agents",
        names.arg = c("0", "1", "2", "3", "4", "5", "6", "7"),
        col=c("darkblue","red"),
        legend = c("Small-Wolrd", "Scale-Free"), beside=TRUE)

##Analysis
mean(distances(scalefree, 1))
mean_distance(scalefree, directed = F)
mean_distance(smallworld, directed = F)
V(scalefree)
E(scalefree)
shortest_paths(scalefree, 1, 5)
all_shortest_paths(scalefree, 1, 5)

