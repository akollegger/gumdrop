START n=node(0) CREATE UNIQUE (friend {name:"Andreas"})-[:KNOWS]->n;
START n=node(*) RETURN n;
