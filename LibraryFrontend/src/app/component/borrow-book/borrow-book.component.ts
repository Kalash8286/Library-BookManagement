import { Component, OnInit } from '@angular/core';
import { BorrowBook } from '../../model/borrowBook';
import { Book } from '../../model/book';
import { ActivatedRoute, Router } from '@angular/router';
import { LibraryApiService } from '../../service/library-api.service';

@Component({
  selector: 'app-borrow-book',
  standalone: false,
  templateUrl: './borrow-book.component.html',
  styleUrl: './borrow-book.component.css'
})
export class BorrowBookComponent implements OnInit {

  book = new Book();
  borrowBook = new BorrowBook();
  bookId!: string | null;
  // showMsg = '';
  // showMsgColor = '';


  constructor(private route: ActivatedRoute, private libraryAPI: LibraryApiService, private router: Router) { }

  ngOnInit(): void {
    this.bookId = this.route.snapshot.paramMap.get('bookId');
    if (Number(this.bookId) > 0) {
      this.libraryAPI.getBookByID(Number(this.bookId)).subscribe(
        response => {
          this.book = response;
        }
      )
    }
  }

  callBorrowBook() {
    this.libraryAPI.borrowBook(Number(this.bookId), this.borrowBook).subscribe(
      response => this.router.navigate(['/view-book']),
      error => this.book.showMsg = error.error.message

    );
  }




}
