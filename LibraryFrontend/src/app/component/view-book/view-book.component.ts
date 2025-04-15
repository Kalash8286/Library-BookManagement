import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LibraryApiService } from '../../service/library-api.service';
import { Book } from '../../model/book';
import { BorrowBook } from '../../model/borrowBook';

@Component({
  selector: 'app-view-book',
  standalone: false,
  templateUrl: './view-book.component.html',
  styleUrl: './view-book.component.css'
})
export class ViewBookComponent implements OnInit {

  // books = [ 
  //   {bookId: 1, bookAuthor: 'fakeName', bookTitle: 'fakeTitle'},
  //   {bookId: 2, bookAuthor: 'fakeName2', bookTitle: 'fakeTitle2'}
  // ]

  books !: Book[];
  userEmail = new BorrowBook();
  showMsg = '';

  constructor(private router: Router, private libraryAPI: LibraryApiService) {
  }

  ngOnInit(): void {
    this.libraryAPI.getAllBooks().subscribe(
      response => {
        this.books = response;        
      },
      error => console.log(error)
    );
  }

  editBook(bookId: number) {
    this.router.navigate(['/edit-book', { bookId: bookId }]);
  }

  borrowBook(bookId: number) {
    this.router.navigate(['/borrow-book', { bookId: bookId }]);
  }

  returnBook(book: Book) {
    this.libraryAPI.returnBook(book.id).subscribe(
      response => { book.borrowed = false;
          book.borrowedBy=null;
       },
      error => book.showMsg = error.error[0].message
    );
  }


  deleteBook(book: Book) {
    this.libraryAPI.deleteBook(book.id).subscribe(
      response => this.ngOnInit(),
      error => book.showMsg = error.error[0].message
    );
  }

}
