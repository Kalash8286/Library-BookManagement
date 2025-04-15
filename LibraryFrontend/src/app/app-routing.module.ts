import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './component/add-book/add-book.component';
import { ViewBookComponent } from './component/view-book/view-book.component';
import { EditBookComponent } from './component/edit-book/edit-book.component';
import { BorrowBookComponent } from './component/borrow-book/borrow-book.component';
import { AddUserComponent } from './component/add-user/add-user.component';
import { ViewUserComponent } from './component/view-user/view-user.component';

const routes: Routes = [
  {path: 'add-book', component: AddBookComponent},
  {path: 'view-book', component: ViewBookComponent},
  {path: 'edit-book', component: EditBookComponent},
  {path: 'borrow-book', component: BorrowBookComponent},
  {path: 'add-user', component: AddUserComponent},
  {path: 'view-user', component: ViewUserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
