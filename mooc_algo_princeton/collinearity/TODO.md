## Optimizations :

### BruteCollinearPoints.java:
- [ ] return only the major line segments and not the sub segments between them. Meaning, given 4 pts p, q, r, s which are collinear return only p -> s instead of p -> q , q -> s etc.
- [ ] Optimize to choose all combinations of N points by selecting N Choose 4, instead of N ^ 4
- [ ] Some loops can be eliminated. If first two points are not collinear then the next three loops are useless.
### Fast Collinear Points
- [x] Fails timing tests. Gotta refine the N^2 Log N logic.
- [x] Don't use toString() function to check for line segment equivalence
