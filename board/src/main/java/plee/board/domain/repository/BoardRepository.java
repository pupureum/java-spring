package plee.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plee.board.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
