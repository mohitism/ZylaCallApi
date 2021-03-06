CREATE TABLE IF NOT EXISTS `phone_directory` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Auto generated value. Will be the primary key of the table.',
  `phone_number` VARCHAR(128) NOT NULL COMMENT 'phone number of client',
  `status` VARCHAR(32) NOT NULL COMMENT 'Indicates the status of client \n\nEg :ACTIVE, INACTIVE.',
  `status_changed_on` DATETIME  NULL,
  `status_changed_remarks` VARCHAR(256)  NULL,
  `created_on` DATETIME  NULL,
  `created_by` VARCHAR(64)  NULL,
  `updated_on` DATETIME  NULL,
  `updated_by` VARCHAR(64)  NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_phone_number` (`phone_number` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'phone table stores the details of directory structure of the p' /* comment truncated */ /*latform owner.Each office definition will be as per the hierarchy template definition.*/;