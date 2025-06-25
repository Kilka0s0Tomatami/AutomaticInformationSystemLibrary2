create table stillages(
    stillage_id serial primary key,
    stillage_number int not null,
    stillage_shelfs_count int not null,
    stillage_shelf_cells_count int not null
);



create table shelfs(
    shelf_id serial primary key,
    shelf_number int not null,
    stillage_id int not null,
    shelf_cells_count int not null,
    foreign key (stillage_id) references stillages (stillage_id) on delete cascade
);

create index index_stillage_shelfs on shelfs (stillage_id);



create table cells(
    cell_id serial primary key,
    cell_number int not null,
    shelf_id int not null,
    foreign key (shelf_id) references shelfs (shelf_id) on delete cascade
);

create index index_shelf_cells ON cells (shelf_id);



create table book_editions(
    book_edition_id serial primary key,
    book_edition_title varchar(255) not null,
    book_edition_author varchar(255) not null,
    book_edition_year_publication int not null,
    book_edition_place_publication varchar(255) not null,
    book_edition_udk_number varchar(255),
    book_edition_bbk_number varchar(255)
);

create index index_book_edition_authors on book_editions (book_edition_author);
create index index_book_edition_titles on book_editions (book_edition_title);
create index index_book_edition_year_publications on book_editions (book_edition_year_publication);
create index index_book_edition_place_publications on book_editions (book_edition_place_publication);
create index index_book_edition_udk_numbers on book_editions (book_edition_udk_number);
create index index_book_edition_bbk_numbers on book_editions (book_edition_bbk_number);



create table book_copies(
    book_copy_id serial primary key,
    book_edition_id int not null,
    book_copy_status int,
    book_copy_fond_number int not null unique,
    foreign key (book_edition_id) references book_editions (book_edition_id) on delete cascade
);

create index index_book_copy_editions on book_copies (book_edition_id);
create index index_book_copy_statuses on book_copies (book_copy_status);



create table placed_book_copies(
    book_copy_id int not null unique,
    cell_id int not null unique,
    foreign key (book_copy_id) references book_copies (book_copy_id) on delete cascade,
    foreign key (cell_id) references cells (cell_id) on delete cascade
);

create index index_placed_book_copy_id on placed_book_copies (book_copy_id);
create index index_occupied_cell_id on placed_book_copies (cell_id);



create table roles(
    role_id serial primary key,
    role_name varchar(255)
);



create table users(
    user_id serial primary key,
    user_login varchar(255),
    user_password varchar(255),
    role_id int not null,
    foreign key(role_id) references roles(role_id) on delete cascade
);

create index index_user_logins on users(user_login);



create table lib_cars(
    lib_card_id serial primary key,
    user_id int not null unique,
    lib_card_first_name varchar(255) not null,
    lib_card_second_name varchar(255) not null,
    lib_card_father_name varchar(255),
    lib_card_mobilephone_numner int,
    lib_card_homephone_number int,
    foreign key(user_id) references users (user_id) on delete cascade
);

create index index_lib_card_user_id on lib_cars(user_id);



create table books_on_hands(
    book_copy_id int not null,
    lib_card_id int not null,
    issue_date date not null,
    return_date date not null,
    refund_date date
);
    
create index books_on_hands_book_copy_id on books_on_hands(book_copy_id);
create index books_on_hands_lib_card_id on books_on_hands(lib_card_id);



create table checks(
    check_id serial primary key,
    lib_card_id int not null,
    book_copy_id int not null,
    check_start_date date not null,
    check_finish_date date
);

create index checks_lib_card_id on checks(lib_card_id);
create index checks_book_copy_id on checks(book_copy_id);



/* Функция на автоматическое добавление полок */
create or replace function trigger_function_stillage_shelfs_add()
returns trigger as 
$$
    begin
        for i in 1..new.stillage_shelfs_count loop
            insert into shelfs values
            (default, i, new.stillage_id, new.stillage_shelf_cells_count);
        end loop;
    
        return new;
    end;
$$ language plpgsql;

create trigger trigger_stillage_shelfs_add 
after insert on stillages
for each row 
execute function
    trigger_function_stillage_shelfs_add();



/* Функциия на автоматическое добавление ячеек */
create or replace function trigger_function_shelf_cells_add()
returns trigger as 
$$
    begin
        for i in 1..new.shelf_cells_count loop
            insert into cells values
            (default, i, new.shelf_id);
        end loop;
    
        return new;
    end;
$$ language plpgsql;

create trigger trigger_shelf_cells_add
    after insert on shelfs
    for each row
    execute function trigger_function_shelf_cells_add();