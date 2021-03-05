import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * Created by lcs on 2019-01-10.
 */
@Slf4j
public class TT {

	@Test
	public void testPath(){
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		log.info("test {}", antPathMatcher.match("/abs/**", "/abs/sba/bs"));
		log.info("test {}", antPathMatcher.match("/abs/**", "/aabs/sba/bs"));
		log.info("test {}", antPathMatcher.match("/ab/**/*s", "/ab/sba/bs"));
		log.info("test {}", antPathMatcher.match("/ab/**/*b", "/ab/sba/bs"));
	}
}
