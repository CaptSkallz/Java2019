import java.util.Arrays;

public class Piskvorka {
	private char[][] board;

	public Piskvorka(char[][] m) {
		// board = m;

		// if (m != null) {
		// board = Arrays.copyOf(m, m.length);
		// }
		if (m != null) {
			board = Arrays.copyOf(m, m.length);
			for (int r = 0; r < m.length; r++) {
				if (m[r] != null)
					board[r] = Arrays.copyOf(m[r], m[r].length);
			}
		}
	}

	private boolean isX(int r, int s) {
		if (board == null)
			return false;
		if (0 <= r && r < board.length) {
			if (0 <= s && s < board[r].length) {
				return board[r][s] == 'x';
			} else
				return false;
		} else
			return false;
	}

	private boolean existujevRiadku(int r, int s) {
		for (int i = 0; i < 5; i++) {
			if (!isX(r, s + i))
				return false;
		}
		return true;
	}

	private boolean existujevStlpci(int r, int s) {
		for (int i = 0; i < 5; i++) {
			if (!isX(r + i, s))
				return false;
		}
		return true;
	}

	private boolean existujevDiag1(int r, int s) {
		for (int i = 0; i < 5; i++) {
			if (!isX(r + i, s + i))
				return false;
		}
		return true;
	}

	private boolean existujevDiag2(int r, int s) {
		for (int i = 0; i < 5; i++) {
			if (!isX(r + i, s - i))
				return false;
		}
		return true;
	}

	public boolean existuje() {
		if (board != null) {
			for (int r = 0; r < board.length; r++) {
				if (board[r] != null) {
					for (int s = 0; s < board[r].length; s++) {
						if (existujevRiadku(r, s) || 
							existujevStlpci(r, s) || 
							existujevDiag1(r, s)  || 
							existujevDiag2(r, s))
							return true;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new Piskvorka(
				new char[][] { { ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', 'x', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } })
								.existuje());
		System.out.println(new Piskvorka(
				new char[][] { { ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ 'x', 'x', 'x', 'x', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } })
								.existuje());
		System.out.println(new Piskvorka(
				new char[][] { { ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ 'x', 'x', ' ', 'x', 'x', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', 'x', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', 'x', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', 'x', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } })
								.existuje());
		System.out.println(new Piskvorka(
				new char[][] { { ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ 'x', 'x', ' ', 'x', 'x', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', 'x', 'x', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', 'x', ' ', 'x', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } })
								.existuje());
		System.out.println(new Piskvorka(
				new char[][] { { ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ 'x', 'x', ' ', 'x', 'x', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', 'x', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', 'x', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } })
								.existuje());
		System.out.println(new Piskvorka(new char[][] {}).existuje());
		System.out.println(new Piskvorka(new char[][] { null, null }).existuje());
		System.out.println(new Piskvorka(new char[][] { { ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } }).existuje());
		System.out.println(
				new Piskvorka(new char[][] { { ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ' }, { ' ' } }).existuje());
	}			
}
