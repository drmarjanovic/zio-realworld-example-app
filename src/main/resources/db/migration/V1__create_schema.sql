CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(320) NOT NULL UNIQUE,
    username VARCHAR(32)  NOT NULL UNIQUE,
    bio      TEXT,
    image    TEXT
);

CREATE TABLE followings
(
    id        SERIAL PRIMARY KEY,
    user_id   INTEGER NOT NULL,
    follow_id INTEGER NOT NULL
);

ALTER TABLE followings
    ADD CONSTRAINT fk_followings_user_id_users_id FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE followings
    ADD CONSTRAINT fk_followings_follow_id_users_id FOREIGN KEY (follow_id) REFERENCES users (id);
ALTER TABLE followings
    ADD CONSTRAINT uq_followings_user_id_follow_id UNIQUE (user_id, follow_id);

CREATE TABLE articles
(
    id          SERIAL PRIMARY KEY,
    author_id   INTEGER      NOT NULL,
    title       VARCHAR(128) NOT NULL,
    slug        VARCHAR(36)  NOT NULL UNIQUE,
    body        TEXT         NOT NULL,
    description TEXT         NOT NULL,
    tags        JSONB        NOT NULL,
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);

ALTER TABLE articles
    ADD CONSTRAINT fk_articles_author_id_users_id FOREIGN KEY (author_id) REFERENCES users (id);

CREATE TABLE favorites
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER NOT NULL,
    article_id INTEGER NOT NULL
);

ALTER TABLE favorites
    ADD CONSTRAINT fk_favorites_user_id_users_id FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE favorites
    ADD CONSTRAINT fk_favorites_article_id_articles_id FOREIGN KEY (article_id) REFERENCES articles (id);
ALTER TABLE favorites
    ADD CONSTRAINT uq_favorites_user_id_article_id UNIQUE (user_id, article_id);

CREATE TABLE comments
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER   NOT NULL,
    article_id INTEGER   NOT NULL,
    body       TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE comments
    ADD CONSTRAINT fk_comments_user_id_users_id FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE comments
    ADD CONSTRAINT fk_comments_article_id_articles_id FOREIGN KEY (article_id) REFERENCES articles (id);