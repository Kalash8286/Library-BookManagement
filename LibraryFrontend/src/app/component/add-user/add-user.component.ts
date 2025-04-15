import { Component } from '@angular/core';
import { User } from '../../model/user';
import { LibraryApiService } from '../../service/library-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-user',
  standalone: false,
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  user = new User();
  showMsg = '';
  showMsgColor = '';

  constructor(private libraryAPI: LibraryApiService) { }

  onFormSubmit() {
    this.libraryAPI.addUser(this.user).subscribe(
      response => {
        this.showMsg = 'User successfully added!';
        this.showMsgColor = 'green';
      },

      error => {
        this.showMsg = error.error[0].message;
        this.showMsgColor = 'red';
      }
    )
  }
}
