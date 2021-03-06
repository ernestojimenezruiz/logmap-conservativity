/*******************************************************************************
 * Copyright 2016 by the Department of Computer Science (University of Genova and University of Oxford)
 * 
 *    This file is part of LogMapC an extension of LogMap matcher for conservativity principle.
 * 
 *    LogMapC is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 * 
 *    LogMapC is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 * 
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with LogMapC.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package comparator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import uk.ac.ox.krr.logmap2.indexing.JointIndexManager;
import auxStructures.Pair;

public class ViolationComparator extends AbstractViolationComparator {

	private JointIndexManager idx;
	private List<Integer> leaves;
	
	public ViolationComparator(JointIndexManager idx, List<Integer> leaves){
		this.idx = idx;
		this.leaves = leaves;
	}

	@Override
	public int compare(Pair<Integer> o1, Pair<Integer> o2) {
		int res = 0;
		
		Iterator<Integer> itr = leaves.iterator();
		Integer i, i1 = o1.getFirst(), i2 = o2.getFirst();
		
		boolean sub1, sub2;
		
		while(itr.hasNext()){
			i = itr.next();
			
			sub1 = idx.isSubClassOf(i, i1);
			sub2 = idx.isSubClassOf(i, i2);
			
			if(sub1 && sub2)
				return 0;
			if(sub1)
				return 1;
			if(sub2)
				return -1;
		}
		
		return res;
	}
}