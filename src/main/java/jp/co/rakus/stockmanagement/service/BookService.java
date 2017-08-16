package jp.co.rakus.stockmanagement.service;

import java.util.List;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 書籍関連サービスクラス.
 * 
 * @author kanekoshuhei
 *
 */
@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findOne(Integer id) {
		return bookRepository.findOne(id);
	}

	// public Book save(Book book){
	// return bookRepository.save(book);
	// }

	public Book update(Book book) {
		return bookRepository.update(book);
	}

	// public void delete(Integer id){
	// bookRepository.delete(id);
	// }

	/**
	 * 最大IDを取得し、次に登録されるIDを返す.
	 * 
	 * @return 次に登録されるID
	 */
	public Integer getNextRegistId() {

		Integer maxId = bookRepository.getMaxId();

		if (maxId == null) {
			return 1;
		} else {
			return maxId + 1;
		}
	}

	/**
	 * 書籍登録.
	 * 
	 * @param book
	 *            書籍
	 * @return 登録書籍
	 */
	public Book regist(Book book) {
		return bookRepository.insert(book);
	}
}
