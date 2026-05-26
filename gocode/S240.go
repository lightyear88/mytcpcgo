package main

func searchMatrix(matrix [][]int, target int) bool {
	m, n := len(matrix), len(matrix[0])

	row, col := 0, n-1
	for row < m && col >= 0 {
		if target == matrix[row][col] {
			return true
		} else if target >= matrix[row][col] {
			row++
		} else {
			col--
		}
	}
	return false
}
