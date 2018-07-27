package joel.llosa;

import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, String> {
	@Trace
	Blog findByTitle(String title);
}
