import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { LibraryApiService } from '../../service/library-api.service';

@Component({
  selector: 'app-view-user',
  standalone: false,
  templateUrl: './view-user.component.html',
  styleUrl: './view-user.component.css'
})
export class ViewUserComponent implements OnInit {

  users !: User[];
  showMsg = '';

  constructor(private libraryAPI: LibraryApiService) {
  }

  ngOnInit(): void {
    this.libraryAPI.getAllUser().subscribe(
      response => {
        this.users = response;
      },
      error => console.log(error)
    );
  }


}
