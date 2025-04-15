import { Component } from '@angular/core';
import { Book } from '../../model/book';
import { LibraryApiService } from '../../service/library-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-book',
  standalone: false,
  templateUrl: './add-book.component.html',
  styleUrl: './add-book.component.css'
})
export class AddBookComponent {

  newBook = new Book();
  showMsg = '';
  showMsgColor = '';


  constructor(private libraryAPI: LibraryApiService) { }

  onFormSubmit() {
    this.libraryAPI.addBook(this.newBook).subscribe(
      response => {
        this.showMsg = response.title + ' is successfully added!';
        this.showMsgColor = "green";
      },
      error => {
        this.showMsg = error.error[0].message;
        this.showMsgColor = "red";
      }
    );
  }

}
