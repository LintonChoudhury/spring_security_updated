package in.linton.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.linton.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{
	
	Optional<UserInfo> findByUsername(String username);

}
