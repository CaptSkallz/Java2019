import java.util.Arrays;

// all non-zero elements are stored in array vals, their
// location is implied by value in array pos at the corresponding
// index, see below. The array pos[] is sorted.
public class SparseMatrix 
{
    private long pos[];   // row * width + col 
	private long vals[];
	int width;
	int height;
		
	public SparseMatrix(int nrows, int ncols)
	{
	  width = ncols;
	  height = nrows;
	  pos = new long[1];
	  vals = new long[1];
	  pos[0] = 0;
	  vals[0] = 0;
	}

	// set element at given location to specified value
	public void setElem(int row, int col, long val)
	{
		long p = row * width + col;
		int i = Arrays.binarySearch(pos, p);
		if (i >= 0) vals[i] = val;
		else
		{
			i = -1 - i;
			pos = Arrays.copyOf(pos, pos.length + 1);
			vals = Arrays.copyOf(vals, vals.length + 1);
			for (int j = pos.length - 1; j > i; j--)
			{
				pos[j] = pos[j-1];
				vals[j] = vals[j-1];
			}
			pos[i] = p;
			vals[i] = val;
		}
	}
	
	// read element from specified location
	public long getElem(int row, int col)
	{
		long p = row * width + col;
		int i = Arrays.binarySearch(pos, p);
		if (i >= 0) return vals[i];
		return 0;		
	}
	
	// add two sparse matrices
	public SparseMatrix add(SparseMatrix m) throws Exception
	{
		if ((width != m.width) || (height != m.height))
			throw new Exception("Attempt to add matrices of incompatible dimensions");
		int newsize = vals.length;
		int i = 0;
		int j = 0;
		// in the first passs, we will determine the number of non-zero elements
		// in the resulting matrix
		while ((i < vals.length) && (j < m.vals.length))
		{
			if (pos[i] == m.pos[j]) { i++; j++; }
			else if (pos[i] < m.pos[j]) i++;
			else { j++; newsize++; }
		}
		newsize += m.vals.length - j;
		SparseMatrix n = new SparseMatrix(width, height);
		n.pos = new long[newsize];
		n.vals = new long[newsize];
		i = 0; j = 0;
		int k = 0;
		// now fill the resulting matrix
		while ((i < vals.length) && (j < m.vals.length))
		{
			if (pos[i] == m.pos[j]) 
			{ 
				n.pos[k] = pos[i];
				n.vals[k] = vals[i] + m.vals[j];
				i++; j++; k++;
			}
			else if (pos[i] < m.pos[j]) 
			{
				n.pos[k] = pos[i];
				n.vals[k] = vals[i];
				i++; k++;
			}
			else 
			{ 
				n.pos[k] = m.pos[j];
				n.vals[k] = m.vals[j];
				j++; k++;
			}
		}
		// copy the remaining elements
		while (i < vals.length)
		{
			n.pos[k] = pos[i];
			n.vals[k] = vals[i];
			i++; k++;
		}
		while (j < vals.length)
		{
			n.pos[k] = m.pos[j];
			n.vals[k] = m.vals[j];
			j++; k++;
		}
		return n;
	}

	// multiply two sparse matrices
	public SparseMatrix mult(SparseMatrix m) throws Exception
	{
		if (width != m.height) 
			throw new Exception("Attempt to multiply matrices of incompatible dimensions");
		SparseMatrix n = new SparseMatrix(m.width, height);
		// look at all value pairs
		for (int i = 0; i < pos.length; i++)
			for (int j = 0; j < pos.length; j++)
			{
				int r1 = (int)(pos[i] / width);
				int c1 = (int)(pos[i] % width);
				int r2 = (int)(m.pos[j] / m.width);
				int c2 = (int)(m.pos[j] % m.width);
				// if their product appears in any sum, add them to the result
				if (c1 == r2) n.setElem(r1, c2, n.getElem(r1, c2) + vals[i] * m.vals[j]);
			}
		return n;
	}
	
	// only for tyesting (assumes values are 0..99)
	public String toString()
	{
		StringBuilder b = new StringBuilder((width * 3 + 1) * height);
        for (int i = 0; i < (width * 3 + 1) * height; i++) b.append(' ');
        for (int i = 0; i < height; i++) b.setCharAt(i * (width * 3 + 1) + width * 3, '\n');
        for (int i = 0; i < pos.length; i++)
        {
        	b.setCharAt((int)((pos[i] / width) * (width * 3 + 1) + (pos[i] % width) * 3), (char)('0' + vals[i] / 10));
        	b.setCharAt((int)((pos[i] / width) * (width * 3 + 1) + (pos[i] % width) * 3 + 1), (char)('0' + vals[i] % 10));
        }
        return b.toString();
	}
	
	// testing program
	public static void main(String args[]) throws Exception
	{
		SparseMatrix m1 = new SparseMatrix(10,10);
		SparseMatrix m2 = new SparseMatrix(10,10);
		
		m1.setElem(0, 0, 1);
		m1.setElem(0, 1, 1);
		m1.setElem(1, 4, 1);
		m1.setElem(2, 3, 1);
		m1.setElem(6, 3, 1);
		m1.setElem(8, 6, 1);

		m2.setElem(0, 0, 2);
		m2.setElem(3, 2, 2);
		m2.setElem(3, 8, 2);
		m2.setElem(5, 1, 2);
		m2.setElem(7, 6, 2);
		m2.setElem(8, 8, 2);

		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m1.add(m2));
		System.out.println(m1.mult(m2));
	}

}
