import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddBookComponent } from './component/add-book/add-book.component';
import { FormsModule } from '@angular/forms';
import { ViewBookComponent } from './component/view-book/view-book.component';
import { EditBookComponent } from './component/edit-book/edit-book.component';
import { HttpClient, HttpClientModule, provideHttpClient } from '@angular/common/http';
import { BorrowBookComponent } from './component/borrow-book/borrow-book.component';
import { AddUserComponent } from './component/add-user/add-user.component';
import { ViewUserComponent } from './component/view-user/view-user.component';


@NgModule({
  declarations: [
    AppComponent,
    AddBookComponent,
    ViewBookComponent,
    EditBookComponent,
    BorrowBookComponent,
    AddUserComponent,
    ViewUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule    
  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
