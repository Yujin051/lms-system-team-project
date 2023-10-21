package org.example.repository;

import org.example.entity.DirectMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 임승범
 */

@Repository
public interface DirectMsgRepository extends JpaRepository<DirectMsg , Long> {

}
