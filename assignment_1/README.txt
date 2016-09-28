csc421 - Assignment #1
Brett Blashko
V00759982
Charlotte Fedderly
V00729142

====================================================================================
Problem 1:
====================================================================================

//Breadth First Statistics
BreadthFirstTreeSearch:		(cost=568.0, expansions=27)	Timisoara Arad Sibiu Fagaras Bucharest 
BreadthFirstGraphSearch:	(cost=568.0, expansions=12)	Timisoara Arad Sibiu Fagaras Bucharest 


//Depth Limited and Iterative Deepening Statistics
Depth Limit = 4

DepthLimitedTreeSearch:	        (cost=568.0, expansions=17)	Timisoara Arad Sibiu Fagaras Bucharest 
DepthLimitedGraphSearch:	(cost=568.0, expansions=4)	Timisoara Arad Sibiu Fagaras Bucharest 
IterativeDeepeningTreeSearch:	(cost=568.0, expansions=8)	Timisoara Arad Sibiu Fagaras Bucharest 
IterativeDeepeningGraphSearch:	(cost=568.0, expansions=7)	Timisoara Arad Sibiu Fagaras Bucharest 

Depth Limit = 5

DepthLimitedTreeSearch:	        (cost=536.0, expansions=25)	Timisoara Arad Sibiu Rimnicu Pitesti Bucharest 
DepthLimitedGraphSearch:	null
IterativeDeepeningTreeSearch:	(cost=568.0, expansions=13)	Timisoara Arad Sibiu Fagaras Bucharest 
IterativeDeepeningGraphSearch:	(cost=568.0, expansions=10)	Timisoara Arad Sibiu Fagaras Bucharest 

Depth Limit = 6

DepthLimitedTreeSearch:	        (cost=615.0, expansions=16)	Timisoara Lugoj Mehadia Drobeta Craiova Pitesti Bucharest 
DepthLimitedGraphSearch:	(cost=725.0, expansions=7)	Timisoara Arad Zerind Oradea Sibiu Fagaras Bucharest 
IterativeDeepeningTreeSearch:	(cost=568.0, expansions=12)	Timisoara Arad Sibiu Fagaras Bucharest 
IterativeDeepeningGraphSearch:	(cost=568.0, expansions=5)	Timisoara Arad Sibiu Fagaras Bucharest 

====================================================================================
Problem 2:
====================================================================================

AStarTreeSearch:				(cost=536.0, expansions=11)	Timisoara Arad Sibiu Rimnicu Pitesti Bucharest 
Timisoara(g=0.0, h=329.0, f=329.0) order=0
	Lugoj(g=111.0, h=244.0, f=355.0) order=1
		Timisoara(g=222.0, h=329.0, f=551.0) order=-1
		Mehadia(g=181.0, h=241.0, f=422.0) order=2
			Lugoj(g=251.0, h=244.0, f=495.0) order=4
				Timisoara(g=362.0, h=329.0, f=691.0) order=-1
				Mehadia(g=321.0, h=241.0, f=562.0) order=-1
			Drobeta(g=256.0, h=242.0, f=498.0) order=5
				Mehadia(g=331.0, h=241.0, f=572.0) order=-1
				Craiova(g=376.0, h=160.0, f=536.0) order=10
					Pitesti(g=514.0, h=100.0, f=614.0) order=-1
					Rimnicu(g=522.0, h=193.0, f=715.0) order=-1
					Drobeta(g=496.0, h=242.0, f=738.0) order=-1
	Arad(g=118.0, h=366.0, f=484.0) order=3
		Timisoara(g=236.0, h=329.0, f=565.0) order=-1
		Sibiu(g=258.0, h=253.0, f=511.0) order=6
			Rimnicu(g=338.0, h=193.0, f=531.0) order=7
				Pitesti(g=435.0, h=100.0, f=535.0) order=9
					Rimnicu(g=532.0, h=193.0, f=725.0) order=-1
					Bucharest(g=536.0, h=0.0, f=536.0) order=11
					Craiova(g=573.0, h=160.0, f=733.0) order=-1
				Sibiu(g=418.0, h=253.0, f=671.0) order=-1
				Craiova(g=484.0, h=160.0, f=644.0) order=-1
			Fagaras(g=357.0, h=176.0, f=533.0) order=8
				Sibiu(g=456.0, h=253.0, f=709.0) order=-1
				Bucharest(g=568.0, h=0.0, f=568.0) order=-1
			Arad(g=398.0, h=366.0, f=764.0) order=-1
			Oradea(g=409.0, h=380.0, f=789.0) order=-1
		Zerind(g=193.0, h=374.0, f=567.0) order=-1

AStarGraphSearch:				(cost=536.0, expansions=10)	Timisoara Arad Sibiu Rimnicu Pitesti Bucharest 
Timisoara(g=0.0, h=329.0, f=329.0) order=0
	Lugoj(g=111.0, h=244.0, f=355.0) order=1
		Timisoara(g=222.0, h=329.0, f=551.0) order=-1
		Mehadia(g=181.0, h=241.0, f=422.0) order=2
			Lugoj(g=251.0, h=244.0, f=495.0) order=-1
			Drobeta(g=256.0, h=242.0, f=498.0) order=4
				Mehadia(g=331.0, h=241.0, f=572.0) order=-1
				Craiova(g=376.0, h=160.0, f=536.0) order=9
					Pitesti(g=514.0, h=100.0, f=614.0) order=-1
					Rimnicu(g=522.0, h=193.0, f=715.0) order=-1
					Drobeta(g=496.0, h=242.0, f=738.0) order=-1
	Arad(g=118.0, h=366.0, f=484.0) order=3
		Timisoara(g=236.0, h=329.0, f=565.0) order=-1
		Sibiu(g=258.0, h=253.0, f=511.0) order=5
			Rimnicu(g=338.0, h=193.0, f=531.0) order=6
				Pitesti(g=435.0, h=100.0, f=535.0) order=8
					Rimnicu(g=532.0, h=193.0, f=725.0) order=-1
					Bucharest(g=536.0, h=0.0, f=536.0) order=-1
					Craiova(g=573.0, h=160.0, f=733.0) order=-1
				Sibiu(g=418.0, h=253.0, f=671.0) order=-1
				Craiova(g=484.0, h=160.0, f=644.0) order=-1
			Fagaras(g=357.0, h=176.0, f=533.0) order=7
				Sibiu(g=456.0, h=253.0, f=709.0) order=-1
				Bucharest(g=568.0, h=0.0, f=568.0) order=-1
			Arad(g=398.0, h=366.0, f=764.0) order=-1
			Oradea(g=409.0, h=380.0, f=789.0) order=-1
		Zerind(g=193.0, h=374.0, f=567.0) order=-1

====================================================================================
Problem 3:
====================================================================================

====================================================================================
Problem 4:
====================================================================================

====================================================================================
Problem 5:
====================================================================================
