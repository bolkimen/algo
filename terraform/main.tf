terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.27"
    }
  }

  required_version = ">= 0.14.9"
}

provider "aws" {
  profile = "default"
  region  = "us-west-2"
}

variable "bucket_name" {
  description = "Bucket name."
  default = "terra-a751e931-672c-401f-ba91-0ef279336c65"
}

variable "tags" {
  description = "Tags."
  type        = map(string)
  default     = {}
}

variable "versioning" {
  description = "Enable versioning support."
  default     = false
}

variable "force_destroy" {
  description = "Allow deleting the bucket with its content."
  default     = false
}

resource "aws_s3_bucket" "origin" {
  bucket        = var.bucket_name
  acl           = "private"
  tags          = var.tags
  force_destroy = var.force_destroy
  versioning {
    enabled = var.versioning
  }
}
