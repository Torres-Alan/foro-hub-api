CREATE TABLE IF NOT EXISTS topics (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  message VARCHAR(500) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(20) NOT NULL,
  author VARCHAR(100) NOT NULL,
  course VARCHAR(100) NOT NULL,

  CONSTRAINT chk_topics_status CHECK (status IN ('OPEN','ANSWERED','CLOSED','ARCHIVED')),
  CONSTRAINT uq_topics_title_message UNIQUE (title, message),

  INDEX idx_topics_created_at (created_at),
  INDEX idx_topics_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
