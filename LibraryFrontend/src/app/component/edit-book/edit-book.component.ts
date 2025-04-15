import { Component, OnInit } from '@angular/core';
import { Book } from '../../model/book';
import { ActivatedRoute } from '@angular/router';
import { LibraryApiService } from '../../service/library-api.service';

@Component({
  selector: 'app-edit-book',
  standalone: false,
  templateUrl: './edit-book.component.html',
  styleUrl: './edit-book.component.css'
})
export class EditBookComponent implements OnInit {

  book = new Book();
  bookId!: string | null;  
  showMsg = '';
  showMsgColor = '';


  constructor(private route: ActivatedRoute, private libraryAPI: LibraryApiService) { }

  ngOnInit(): void {
    this.bookId = this.route.snapshot.paramMap.get('bookId');
    if (Number(this.bookId)> 0) {
      this.libraryAPI.getBookByID(Number(this.bookId)).subscribe(
        response => {this.book = response; console.log(response);}
         
      )
    }

  }

  onFormSubmit() {
    this.libraryAPI.addBook(this.book).subscribe(
      response => {
        this.showMsg = response.title + ' is successfully updated!';
        this.showMsgColor = "green";
      },
      error => {
        
        this.showMsg = error.error[0].message;
        this.showMsgColor = "red";
      }
    );
  }
}
