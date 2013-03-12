// get all nodes
start n=node(*) return n;

// ooh, comments and spaces
START n=node(0) match (n)-[r]-(m) RETURN r,m;

